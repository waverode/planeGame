package com.tedu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tedu.frame.MyJPanel;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.vo.BigFire;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.EnemyFire;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.PlayerFire;
import com.tedu.model.vo.Power;
import com.tedu.model.vo.Prop;
import com.tedu.model.vo.ScoreBoard;
import com.tedu.model.vo.SuperElement;

//java�ǵ��̳У���ʵ�֡�ͨ�� �ڲ���ķ�ʽ���ֲ����̳е�ȱ��
public class GameThread extends Thread{
//	��ʱ����
	private int time;
	private  boolean flag=true;
//	��������� �� ˼��Ľ��� ����ͨ���ܶ����Ŀ����
//	�����Ŀ���࣬�� �ع�����Ŀ
	public void run(){
		while(flag){   //��Ϸ�������
	//		��ѭ���������б�����״̬�����п���
	//		1.���ص�ͼ������
			loadElement();
	//		2.��ʾ�����ͼ(����,�Զ���(�ƶ�����ײ))
			time=0;
			runGame();
	//		3.��������ͼ
			overGame();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void runGame() {
		while(true){  //ÿ���������ʱ���״̬
//			List<SuperElement>list=ElementManager.getManager().getElementList("play");
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
	//	List<String> lists=new ArrayList<>();
//	//		lists.addAll(set);
//			for(int j=0;j<lists.size();j++){
//				String key=lists.get(j);
				
			for(String key:set){//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز����� ���ӻ���ɾ��
				List<SuperElement> list=map.get(key);
				for(int i=list.size()-1;i>=0;i--){
//				for(int i=0;i<list.size();i++){
					list.get(i).update(time);
					if(!list.get(i).isVisible()){
						list.remove(i--);
					}
				}
				
			}
//			ʹ��һ�������ķ����������ж�
			PK();
//			д�ɻ�����ӵ� list��map  //��Ϸ�����̿��� 
			linkGame();
			//������ͨ�ص� ���� runGame����
			if(ElementManager.getManager().getElementList("play").isEmpty())
				break;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
		}
	}
	private void PK() {
		List<SuperElement> playFirelist=
				ElementManager.getManager().getElementList("playFire");
		List<SuperElement> enemylist=
				ElementManager.getManager().getElementList("enemyList");
		List<SuperElement> playlist=
				ElementManager.getManager().getElementList("play");
		List<SuperElement> enemyFirelist=
				ElementManager.getManager().getElementList("enemyFire");
		List<SuperElement> proplist=ElementManager.getManager()
				.getElementList("proplist");
//      ��ҳԵ���
		playerPKprop(playlist, proplist);
//		����ӵ�����˺�
		FirePKEnemy(playFirelist, enemylist);
//		���д��ӵ�
		FirePKFire(playFirelist,enemyFirelist);
//		�ӵ������
		FirePKPlay(enemyFirelist, playlist);
//		�ɻ����ɻ�
		EnemyPKPlay(enemylist,playlist);
		
//		listPK(list1,list2);
//		listPK(list1, list4);
//		for(int i=0;i<list3.size();i++){
//			for(int j=0;j<list4.size();j++){
//		 		if(list3.get(i).gamePK(list4.get(j))){
//					Player player=(Player)(list3.get(i));
//					System.out.println(player.getHp());
//					EnemyFire enemyFire =(EnemyFire)(list4.get(j));
//					player.setHp(player.getHp()-enemyFire.getPower());
//					enemyFire.setVisible(false);
//				}
//			}
//		}
//		���Ծ��бȽ�
//		for(int i=0;i<list1.)
	}
	private void FirePKFire(List<SuperElement> playFirelist, List<SuperElement> enemyFirelist) {
		for(int i=0;i<playFirelist.size();i++){
			for(int j=0;j<enemyFirelist.size();j++){
				if(playFirelist.get(i).gamePK(enemyFirelist.get(j)))
				{PlayerFire fire=(PlayerFire)(playFirelist.get(i));
				if(fire instanceof BigFire) 
				enemyFirelist.get(j).setVisible(false);	
				}
			}	
		}
	}	
		
	private void playerPKprop(List<SuperElement> playlist,
			List<SuperElement> proplist) {
		for(int i=0;i<proplist.size();i++) {
			for(int j=0;j<playlist.size();j++) {
				if(proplist.get(i).gamePK(playlist.get(j))) {
					Prop prop=(Prop)proplist.get(i);
					Player player=(Player)playlist.get(j);
					
					String s=prop.getPropName();
					String []arr=s.split(",");
					System.out.println(arr[0]);
					switch (prop.getPropName()) {
					case "life1":  //�������ֵ
						player.setHp(player.getHp()+100);
						player.setTipLifeTime(30);
						break;
					case "up1":  //��������
						if(player.getLevel()<3) {
						    player.setLevel(player.getLevel()+1);
						    player.setTipLevelTime(30);
						}
						else 
							break;   
						break;
					case "skill1":   //�������ֵ
						List<SuperElement> powerlist=ElementManager.getManager().getElementList("power");
						Power power=(Power)powerlist.get(0);
						power.setPower(power.getPower()+500);
						power.setTipTime(30);
						break;
					case "skill2":   //��û���
						player.setUseShield(true);
						break;
					}
					prop.setVisible(false);
				}
			}
		}
	}
private void EnemyPKPlay(List<SuperElement> enemylist, 
		List<SuperElement> playlist) {
	for(int i=0;i<enemylist.size();i++){
		for(int j=0;j<playlist.size();j++){
			if(enemylist.get(i).gamePK(playlist.get(j))){
				Enemy enemy = (Enemy)(enemylist.get(i));
				Player player = (Player)(playlist.get(j));
				enemy.setHp(enemy.getHp()-5);
				if(enemy.getHp()<0) {
					//�ӷ�
					ScoreBoard sc = (ScoreBoard)(ElementManager.getManager().getMap().get("score").get(0));
					sc.setScore(sc.getScore()+enemy.getScore());
					enemy.setVisible(false);
				}
				if(!player.isUseShield())
				player.setHp(player.getHp()-5);
			}
		}
	}
	}
private void FirePKPlay(List<SuperElement> enemyFirelist, 
		List<SuperElement> playlist) {
	for(int i=0;i<enemyFirelist.size();i++){
		for(int j=0;j<playlist.size();j++){
			if(enemyFirelist.get(i).gamePK(playlist.get(j))){
				Player player = (Player)(playlist.get(j));
				EnemyFire ef = (EnemyFire)(enemyFirelist.get(i));
				
				if(!player.isUseShield())
				player.setHp(player.getHp()-ef.getPower());
				
				ef.setVisible(false);
			}
		}	
	}
		
	}
//	���ֵĴ��� �ǿ����ظ�ʹ�õġ�
	public void FirePKEnemy(List<SuperElement> firelist,
			List<SuperElement> enemylist){
		for(int i=0;i<firelist.size();i++){
			for(int j=0;j<enemylist.size();j++){
				if(firelist.get(i).gamePK(enemylist.get(j))){
					PlayerFire fire=(PlayerFire)(firelist.get(i));
					Enemy enemy = (Enemy)(enemylist.get(j));
					enemy.setHp(enemy.getHp()-fire.getPower());
					if(enemy.getHp()<0) {
						//�ӷ�
						ScoreBoard sc = (ScoreBoard)(ElementManager.getManager().getMap().get("score").get(0));
						sc.setScore(sc.getScore()+enemy.getScore());
						List<SuperElement> powerlist=ElementManager.getManager().getElementList("power");
						Power power=(Power)powerlist.get(0);
						power.setPower(power.getPower()+10);
						enemy.setVisible(false);}
					if(fire instanceof BigFire) {}										
					else //���в�����ʧ
					fire.setVisible(false);
				}
			}	
		}
	}
	
	
	
	//��Ϸ�����̿��� 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	
	private void overGame() {
		MyJPanel.setDead(true);
		flag=false;
	}
//	���ƽ���,���ǣ���Ϊ ���ƣ��벻Ҫ�Ӵ� load
	private void loadElement() {
		ElementManager.getManager().load();
		
	}
	
}
