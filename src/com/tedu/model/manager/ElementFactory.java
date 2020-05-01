package com.tedu.model.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.BackGround;
import com.tedu.model.vo.BackGround1;
import com.tedu.model.vo.BigFire;
import com.tedu.model.vo.Boss;
import com.tedu.model.vo.BossFire;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.EnemyFire;
import com.tedu.model.vo.Explosion;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.PlayerFire;
import com.tedu.model.vo.PlayerLife;
import com.tedu.model.vo.Power;
import com.tedu.model.vo.Prop;
import com.tedu.model.vo.Shield;
import com.tedu.model.vo.SuperElement;

/**
 * �������ݲ�����ͬ���Զ���ȡ ��Դ����� vo�������ݣ��洢�� Ԫ�ع�����
 * 	���������ã����Ƚϸ��ӵ� ���췽ʽ ���з�װ
 */
public class ElementFactory {
	
	//�����ļ���ȷ�����ֵ�λ��ʱ���ô˷���
	public static SuperElement elementFactory(String name){
		Map<String, List<String>> playmap=
			ElementLoad.getElementLoad().getPlaymap();
		Map<String, List<String>> backgroundmap=ElementLoad.getElementLoad().
				getBackgroundmap();
		List<String> list1=ElementLoad.getElementLoad().getGameList();
		List<String> list2=ElementLoad.getElementLoad().getProplist();
		List<String> playlist=new ArrayList<>();
		List<String> backlist=new ArrayList<>();
		List<String> backlist1=new ArrayList<>();
		switch(name){
		case "onePlayer":
			playlist=playmap.get(name);	
			String s=playlist.get(0);//playerA,playFire,150,300,40,40
			return Player.createPlayer(s);
		case "enemy":
			String str=list1.get(list1.size()-1);
			return Enemy.createEnemey(str);
		case "boss":
			str=list1.get(list1.size()-1);
			return Boss.createBoss(str);
		case "background0":
			backlist=backgroundmap.get(name);
			String s1=backlist.get(0);
			return BackGround.createBackGround(s1);
		case "background1":
			backlist1=backgroundmap.get(name);
			String s2=backlist1.get(0);
			return BackGround1.createBackGround1(s2);
		case "prop":
			String s3=list2.get(list2.size()-1);
			//System.out.println(s3);
			return Prop.createProp(s3);
		case "playLife":
		    return PlayerLife.createPlayerLife();
		case "power":
			return Power.createPower();
		}
		
		return null;
	}
	//�����ļ�δȷ������λ�ã���Ҫ���ݵ����������ȷ�����괴�����ʱ����ô˷���
	public static SuperElement elementFactory(int x,int y,String name) {
		Map<String, List<String>> othervomap=
				ElementLoad.getElementLoad().getOthervomap();
		Map<String, List<String>> playfiremap=
				ElementLoad.getElementLoad().getPlayfiremap();
		Map<String, List<String>> enemyfiremap=
				ElementLoad.getElementLoad().getEnemyfiremap();
		List<String> othervolist=new ArrayList<>();
		List<String> playfirelist=new ArrayList<>();
		List<String> enemyfirelist=new ArrayList<>();
		List<String> shieldlist=new ArrayList<>();
		//����������ӵ���ʱ��arr[0]Ϊ�ӵ����ͣ�arr[1]Ϊ�ӵ��ķ���
		//��arr[0]��name����arr[1]��һЩ����
		String []arr = name.split(",");
		name=arr[0];
		switch(name){
		case "explosion":
			othervolist=othervomap.get(name);
			return Explosion.createExplosin(x, y, othervolist.get(0));
		case "BigFire01":
			playfirelist=playfiremap.get(name);
			return BigFire.createBigFire(x,y,playfirelist.get(0));
		case "playFire01":
			playfirelist=playfiremap.get(name);
			return PlayerFire.createPlayerFire(x, y, playfirelist.get(0)+","+arr[1]);
		case "playFire02":
			playfirelist=playfiremap.get(name);
			return PlayerFire.createPlayerFire(x, y, playfirelist.get(0)+","+arr[1]);	
		case "enemyFire01":
			enemyfirelist=enemyfiremap.get(name);
			return EnemyFire.createEnemyFire(x, y, enemyfirelist.get(0)+","+arr[1]);
		case "enemyFire02":
			enemyfirelist=enemyfiremap.get(name);
			return EnemyFire.createEnemyFire(x, y, enemyfirelist.get(0)+","+arr[1]);
		case "enemyFire03":
			enemyfirelist=enemyfiremap.get(name);
			return EnemyFire.createEnemyFire(x, y, enemyfirelist.get(0)+","+arr[1]);
		case "enemyFire04":
			enemyfirelist=enemyfiremap.get(name);
			return EnemyFire.createEnemyFire(x, y, enemyfirelist.get(0)+","+arr[1]);
		case "bossFire01":
			enemyfirelist=enemyfiremap.get(name);
			return BossFire.createBossFire(x, y, enemyfirelist.get(0)+","+arr[1]);
		case "shield":
			shieldlist=othervomap.get(name);
			return Shield.createShield(x, y, shieldlist.get(0));
		
		}
		return null;	
	}

}
