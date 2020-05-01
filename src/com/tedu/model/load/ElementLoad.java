package com.tedu.model.load;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.tedu.model.manager.MoveType;

public class ElementLoad {
	private Map<String,ImageIcon> map;
	private Map<String,List<String>> playmap;
	private Map<String,List<String>> enemymap;
	private Map<String,List<String>> playfiremap;
	private Map<String,List<String>> enemyfiremap;
	private Map<String,List<String>> backgroundmap;
	private Map<String,List<String>> othervomap; //��һЩС�������籬ը�������棬��������
	private List<String> gameList;//��Ϸ�����̿���  ���˱������ֿ���
    private List<String> proplist;  //���߳���
    
	private static ElementLoad load;
	public List<String> getGameList() {
		return gameList;
	}
	//	pro�ļ���ȡ����
	private Properties pro;
	
	private ElementLoad(){
		map=new HashMap<>();
		playmap=new HashMap<>();
		othervomap=new HashMap<>();
		enemyfiremap=new HashMap<>();
		playfiremap=new HashMap<>();
		pro=new Properties();
		gameList=new ArrayList<>();
		backgroundmap=new HashMap<>();
		proplist=new ArrayList<>();
	}
	public static synchronized ElementLoad getElementLoad(){
		if(load==null){
			load=new ElementLoad();
		}
		return load;
	}
//	��ȡ����
	public void readGamepro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/GameRunA.pro");
		pro.clear();
		try {
			pro.load(in);
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				gameList.add(str);
			}
//			System.out.println(gameList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
//	��ȡ��������
	public void readPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/play.pro");
		try {
			pro.clear();
			pro.load(in);
			String str;
			for(Object o:pro.keySet()){
				str=pro.getProperty(o.toString());
				List<String> list=new ArrayList<>();
				list.add(str);
				playmap.put(o.toString(), list);
			}System.out.println(playmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(playmap);
	}
//  ��ȡ����
	public void readPropPro() {
		InputStream inputStream=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/prop.pro");
		pro.clear();
		try {
			pro.load(inputStream);
			for(Object o:pro.keySet()) {
				String string=pro.getProperty(o.toString());
				proplist.add(string);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
//  ��ȡ��ҵ��ӵ��ʹ�������
public void readPlayFirePro() {
	InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/FireForPlayer.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String str=pro.getProperty(o.toString()); 
				List<String> list = new ArrayList<>();
				list.add(str);
				playfiremap.put(o.toString(),list);
			}
			//System.out.println(playfiremap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	

//��ȡ�з����ӵ��ʹ�������
public void readEnemyFirePro() {
InputStream in=ElementLoad.class.getClassLoader()
		.getResourceAsStream("com/tedu/pro/FireForEnemy.pro");
	try {
		pro.clear();
		pro.load(in);
		Set<?> set=pro.keySet();
		for(Object o:set){
			String str=pro.getProperty(o.toString()); 
			List<String> list = new ArrayList<>();
			list.add(str);
			enemyfiremap.put(o.toString(),list);
		}
	//	System.out.println(enemyfiremap);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}	
	
//  ��ȡ�����Ƚϲ��Ǻ���Ҫ��������	
public void readOtherVoPro() {
		
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/otherVo.pro");
			try {
				pro.clear();
				pro.load(in);
				Set<?> set=pro.keySet();
				for(Object o:set){
					String url=pro.getProperty(o.toString());
					List<String> list = new ArrayList<>();
					list.add(url);
					othervomap.put(o.toString(),list);
				}
//				System.out.println(othervomap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//	��ȡͼƬ��Դ
public void readImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/tedu/pro/mapA.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());

				map.put(o.toString(), new ImageIcon(url));
			}
			//System.out.println(map.get("bigFireA"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//��ȡ����������
public void readBackPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/tedu/pro/background.pro");
		try {
			pro.clear();
			pro.load(in);
			
			Set<?> set=pro.keySet();
			for(Object o:set) {
				String url=pro.getProperty(o.toString());
				List<String> list=new ArrayList<>();
				list.add(url);
				backgroundmap.put(o.toString(), list);
			}
			//System.out.println(backgroundmap);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public Map<String, List<String>> getPlaymap() {
		return playmap;
	}
	public Map<String, List<String>> getEnemymap() {
		return enemymap;
	}
	public Map<String, List<String>> getOthervomap() {
		return othervomap;
	}
	public Map<String, List<String>> getPlayfiremap() {
		return playfiremap;
	}
	public void setPlayfiremap(Map<String, List<String>> playfiremap) {
		this.playfiremap = playfiremap;
	}
	public Map<String, List<String>> getEnemyfiremap() {
		return enemyfiremap;
	}
	public void setEnemyfiremap(Map<String, List<String>> enemyfiremap) {
		this.enemyfiremap = enemyfiremap;
	}
	public Map<String, List<String>> getBackgroundmap() {
		return backgroundmap;
	}
	public void setBackgroundmap(Map<String, List<String>> backgroundmap) {
		this.backgroundmap = backgroundmap;
	}
	public List<String> getProplist() {
		return proplist;
	}
	public void setProplist(List<String> proplist) {
		this.proplist = proplist;
	}
	
//	public void test() {
//			String string = "gggg,"+MoveType.down;
//			String []arr = string.split(",");
//			System.out.println(arr[1]);
//		}
//	
//	public static void main(String[] args) {
//		ElementLoad.getElementLoad().readPlayPro();
//		
//	}
//	
	
	
}
