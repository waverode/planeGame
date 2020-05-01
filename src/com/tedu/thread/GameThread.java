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

//java是单继承，多实现。通过 内部类的方式，弥补单继承的缺陷
public class GameThread extends Thread{
//	计时数据
	private int time;
	private  boolean flag=true;
//	代码的熟练 和 思想的进步 都是通过很多的项目锻炼
//	如果项目不多，请 重构老项目
	public void run(){
		while(flag){   //游戏整体进度
	//		死循环，但会有变量（状态）进行控制
	//		1.加载地图，人物
			loadElement();
	//		2.显示人物地图(流程,自动化(移动，碰撞))
			time=0;
			runGame();
	//		3.结束本地图
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
		while(true){  //每个关中玩的时候的状态
//			List<SuperElement>list=ElementManager.getManager().getElementList("play");
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
	//	List<String> lists=new ArrayList<>();
//	//		lists.addAll(set);
//			for(int j=0;j<lists.size();j++){
//				String key=lists.get(j);
				
			for(String key:set){//迭代器在遍历的过程中，迭代器内的元素不可以 增加或者删除
				List<SuperElement> list=map.get(key);
				for(int i=list.size()-1;i>=0;i--){
//				for(int i=0;i<list.size();i++){
					list.get(i).update(time);
					if(!list.get(i).isVisible()){
						list.remove(i--);
					}
				}
				
			}
//			使用一个独立的方法来举行判定
			PK();
//			写飞机的添加到 list到map  //游戏的流程控制 
			linkGame();
			//死亡，通关等 结束 runGame方法
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
//      玩家吃道具
		playerPKprop(playlist, proplist);
//		玩家子弹打敌人和
		FirePKEnemy(playFirelist, enemylist);
//		大招打子弹
		FirePKFire(playFirelist,enemyFirelist);
//		子弹打玩家
		FirePKPlay(enemyFirelist, playlist);
//		飞机碰飞机
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
//		可以举行比较
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
					case "life1":  //获得生命值
						player.setHp(player.getHp()+100);
						player.setTipLifeTime(30);
						break;
					case "up1":  //威力增加
						if(player.getLevel()<3) {
						    player.setLevel(player.getLevel()+1);
						    player.setTipLevelTime(30);
						}
						else 
							break;   
						break;
					case "skill1":   //获得能量值
						List<SuperElement> powerlist=ElementManager.getManager().getElementList("power");
						Power power=(Power)powerlist.get(0);
						power.setPower(power.getPower()+500);
						power.setTipTime(30);
						break;
					case "skill2":   //获得护盾
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
					//加分
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
//	部分的代码 是可以重复使用的。
	public void FirePKEnemy(List<SuperElement> firelist,
			List<SuperElement> enemylist){
		for(int i=0;i<firelist.size();i++){
			for(int j=0;j<enemylist.size();j++){
				if(firelist.get(i).gamePK(enemylist.get(j))){
					PlayerFire fire=(PlayerFire)(firelist.get(i));
					Enemy enemy = (Enemy)(enemylist.get(j));
					enemy.setHp(enemy.getHp()-fire.getPower());
					if(enemy.getHp()<0) {
						//加分
						ScoreBoard sc = (ScoreBoard)(ElementManager.getManager().getMap().get("score").get(0));
						sc.setScore(sc.getScore()+enemy.getScore());
						List<SuperElement> powerlist=ElementManager.getManager().getElementList("power");
						Power power=(Power)powerlist.get(0);
						power.setPower(power.getPower()+10);
						enemy.setVisible(false);}
					if(fire instanceof BigFire) {}										
					else //大招不会消失
					fire.setVisible(false);
				}
			}	
		}
	}
	
	
	
	//游戏的流程控制 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	
	private void overGame() {
		MyJPanel.setDead(true);
		flag=false;
	}
//	控制进度,但是，作为 控制，请不要接触 load
	private void loadElement() {
		ElementManager.getManager().load();
		
	}
	
}
