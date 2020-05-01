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
 * 元素管理器
 * 
 * java设计模式-》单例模式->全局只有一个实例
 * 
 * hashCode();是Object类->集合排序（Set集合,hash散列原理）
 * 
 */
public class ElementManager {
//	集合  NPC元素，场景元素，。。。。
	Map<String,List<SuperElement>> map;//好处是什么？？？
//	初始化
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
		map.put("score", new ArrayList<>());    //分数板
		map.put("background0", new ArrayList<>());
		map.put("background1", new ArrayList<>());
		map.put("power", new ArrayList<>());
		map.put("shield", new ArrayList<>());
	}
//	得到一个完整的 map集合
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	得到一个元素的集合
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	
	
	
	
	
//	单例：需要一个唯一的引用
	private static ElementManager elementManager;
//	构造方法私有化，就只有在本类中可以 new
	private ElementManager(){
		init();
	}
	static{//静态的语句块 是在类加载的时候就会执行
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	提供出来给予外部访问的唯一入口   synchronized 线程保护锁
	public static  ElementManager getManager(){
		return elementManager;
	}
//	加载需要的资源
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readGamepro();
		ElementLoad.getElementLoad().readOtherVoPro();
		ElementLoad.getElementLoad().readPlayFirePro();
		ElementLoad.getElementLoad().readEnemyFirePro();
		ElementLoad.getElementLoad().readBackPro();
	    ElementLoad.getElementLoad().readPropPro();
		//		开放一个 状态，界面可以做  前夕啦（前面的过度信息）
//		......
		map.get("play").add(ElementFactory.elementFactory("onePlayer"));
		//添加一个分数板到资源管理器
		map.get("score").add(new ScoreBoard(200, 10, 110, 20));
		map.get("background0").add(ElementFactory.elementFactory("background0"));
		map.get("background1").add(ElementFactory.elementFactory("background1"));
		map.get("playLife").add(ElementFactory.elementFactory("playLife"));
		map.get("power").add(ElementFactory.elementFactory("power"));
	}
//	控制流程   int time游戏进行时间
	public void linkGame(int time) {
		Random random = new Random();
//		可以拿到流程 list
		List<String> list=ElementLoad.getElementLoad().getGameList();
		List<String> list2=ElementLoad.getElementLoad().getProplist();
		
		if(list.size()==0){
		//list.add(0,RandomEnemy(arr));
			return;//流程已经结束。
		}
//		随机产生道具
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
			if(arr[arr.length-1].equals("1")) //配置文件最后一个表示boss，等于1时为boss
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




