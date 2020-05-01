package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.EnemyA;
import com.tedu.model.vo.EnemyC;
import com.tedu.model.vo.EnemyE;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.ScoreBoard;
import com.tedu.model.vo.SuperElement;

/**
 * Ԫ�ع�����
 * 
 * java���ģʽ-������ģʽ->ȫ��ֻ��һ��ʵ��
 * 
 * hashCode();��Object��->��������Set����,hashɢ��ԭ��
 * 
 */
public class ElementManager {
//	����  NPCԪ�أ�����Ԫ�أ���������
	Map<String,List<SuperElement>> map;//�ô���ʲô������
//	��ʼ��
	protected void init(){
		map=new HashMap<>();
		List<SuperElement> list=new ArrayList<>();
		map.put("play", list);
		List<SuperElement> listA=new ArrayList<>();
		List<SuperElement> listB=new ArrayList<>();
		map.put("enemyList", listA);
		map.put("proplist", listB);
		map.put("playFire", new ArrayList<>());
		map.put("playLife", new ArrayList<>());
		map.put("explosion", new ArrayList<>());
		map.put("enemyFire", new ArrayList<>());
		map.put("score", new ArrayList<>());    //������
		map.put("background0", new ArrayList<>());
		map.put("background1", new ArrayList<>());
		map.put("power", new ArrayList<>());
		map.put("shield", new ArrayList<>());
	}
//	�õ�һ�������� map����
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	�õ�һ��Ԫ�صļ���
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	
	
	
	
	
//	��������Ҫһ��Ψһ������
	private static ElementManager elementManager;
//	���췽��˽�л�����ֻ���ڱ����п��� new
	private ElementManager(){
		init();
	}
	static{//��̬������ ��������ص�ʱ��ͻ�ִ��
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	�ṩ���������ⲿ���ʵ�Ψһ���   synchronized �̱߳�����
	public static  ElementManager getManager(){
		return elementManager;
	}
//	������Ҫ����Դ
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readGamepro();
		ElementLoad.getElementLoad().readOtherVoPro();
		ElementLoad.getElementLoad().readPlayFirePro();
		ElementLoad.getElementLoad().readEnemyFirePro();
		ElementLoad.getElementLoad().readBackPro();
	    ElementLoad.getElementLoad().readPropPro();
		//		����һ�� ״̬�����������  ǰϦ����ǰ��Ĺ�����Ϣ��
//		......
		map.get("play").add(ElementFactory.elementFactory("onePlayer"));
		//���һ�������嵽��Դ������
		map.get("score").add(new ScoreBoard(200, 10, 110, 20));
		map.get("background0").add(ElementFactory.elementFactory("background0"));
		map.get("background1").add(ElementFactory.elementFactory("background1"));
		map.get("playLife").add(ElementFactory.elementFactory("playLife"));
		map.get("power").add(ElementFactory.elementFactory("power"));
	}
//	��������   int time��Ϸ����ʱ��
	public void linkGame(int time) {
		Random random = new Random();
//		�����õ����� list
		List<String> list=ElementLoad.getElementLoad().getGameList();
		List<String> list2=ElementLoad.getElementLoad().getProplist();
		
		if(list.size()==0){
		//list.add(0,RandomEnemy(arr));
			return;//�����Ѿ�������
		}
//		�����������
		if(list2.size()==0){
			switch (random.nextInt(4)) {
			case 1:
				list2.add(0,"life1,10,10,40,40");
				break;
			case 2:
				list2.add(0,"up1,100,10,40,40");
				break;
			case 3:
				list2.add(0,"skill1,200,10,40,40");
				break;
			default:
				list2.add(0,"skill2,300,10,40,40");
				break;
			}
		}
		String s=list.get(list.size()-1);//001=enemyMoveX,enemyA,enemyFire01,20,40,40,40,10,0
		String[] arr=s.split(",");
		int runTime=Integer.parseInt(arr[arr.length-2]);
		
		if(time%200==0) {
		    map.get("proplist").add(ElementFactory.elementFactory("prop"));
			list2.remove(list2.size()-1);
			}
		System.out.println(time);
		System.out.println(list);
		
		if(time>runTime){
			if(arr[arr.length-1].equals("1")) //�����ļ����һ����ʾboss������1ʱΪboss
				{map.get("enemyList").add(ElementFactory.elementFactory("boss"));				
				list.add(0,RandomEnemy(arr));
				}
			else {
				map.get("enemyList").add(ElementFactory.elementFactory("enemy"));		
				list.add(0,RandomEnemy(arr));
				
			}
			list.remove(list.size()-1);
			
			
		}			
		
	}
	
	public String RandomEnemy(String []arr) {
		Random random = new Random();
		int time;
		String newEnemy="";
		newEnemy=arr[0]+","+arr[1]+","+arr[2]+",";
		switch (arr[0]) {
		
		case "enemyMoveX":	
			time=Integer.parseInt(arr[7])+random.nextInt(100);
			newEnemy+=arr[3]+","+random.nextInt(300)+","+arr[5]+","+arr[6]+","+time;
			break;
		case "enemyMoveParabolic":
			time=Integer.parseInt(arr[7])+random.nextInt(100);
			newEnemy+=arr[3]+","+random.nextInt(850)+","+arr[5]+","+arr[6]+","+time;
			break;
		case "enemyMoveSlash":
			time=Integer.parseInt(arr[7])+random.nextInt(100);
			newEnemy+=arr[3]+","+random.nextInt(850)+","+arr[5]+","+arr[6]+","+time;
			break;
		case "bossA":
			time=Integer.parseInt(arr[7])+random.nextInt(800);
			newEnemy+=random.nextInt(500)+","+arr[4]+","+arr[5]+","+arr[6]+","+time;
			break;
		default:
			time=Integer.parseInt(arr[7])+random.nextInt(100);
			newEnemy+=random.nextInt(500)+","+arr[4]+","+arr[5]+","+arr[6]+","+time;
		}		
		newEnemy+=","+arr[arr.length-1];
			return newEnemy;
		}
	
	
}




