package com.tedu.model.vo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class Boss extends Enemy{
	private int hp;
	private int score;
	private String name;
	private ImageIcon img;
	private PlayerFire bossFire;
	
	private int direction=-1; //boss左右移动的方向

	public Boss(int x, int y, int w, int h, String efType, ImageIcon img) {
		super(x, y, w, h, efType, img);
		// TODO 自动生成的构造函数存根
		this.img=img;
		this.hp=5000;
		this.score=5000;
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(), getW(), getH(), null);
		paintHP(g);
	}
	
	public static Boss createBoss(String str) {
		String []arr=str.split(",");
		int x=Integer.parseInt(arr[3]);
		int y=Integer.parseInt(arr[4]);
		int w=Integer.parseInt(arr[5]);
		int h=Integer.parseInt(arr[6]);
		String efType=arr[2];
//		System.out.println(efType);

		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[1]);
		Boss boss=null;
		switch(arr[0]){//在配置文件中可以给与类型
		case "bossA":
			boss=new Boss(x, y, w, h, efType, img);
			break;
		case "bossB":
			boss=new Boss(x, y, w, h, efType, img);
			break;
		default:
			boss=new Boss(x, y, w, h, efType, img);
		}
		return boss;	
	}
	
	@Override
	public void update(int time) {
		// TODO 自动生成的方法存根
		move();
		destroy();
		if(time%10==0)
			addFire(time);
	}
	
	@Override
	public void addFire(int time) {
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
		int random = (int)(Math.random()*100);
		if(time%10==0) {
//		boss 1. 4条子弹
			if(random>10) {
			list.add(ElementFactory.elementFactory(getX()-30, getY(), getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+60, getY(), getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+150, getY(), getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+240, getY(), getEfType()+","+MoveType.down));
			}else {
//		boss 2. 圆圈四散
			list.add(ElementFactory.elementFactory(getX()+150, getY(), getEfType()+","+MoveType.top));
			list.add(ElementFactory.elementFactory(getX()+130, getY()+20, getEfType()+","+MoveType.lefttop));
			list.add(ElementFactory.elementFactory(getX()+170, getY()+20, getEfType()+","+MoveType.righttop));
			list.add(ElementFactory.elementFactory(getX()+110, getY()+40, getEfType()+","+MoveType.left));
			list.add(ElementFactory.elementFactory(getX()+190, getY()+40, getEfType()+","+MoveType.right));
			list.add(ElementFactory.elementFactory(getX()+130, getY()+60, getEfType()+","+MoveType.leftdown));
			list.add(ElementFactory.elementFactory(getX()+170, getY()+60, getEfType()+","+MoveType.rightdown));
			list.add(ElementFactory.elementFactory(getX()+150, getY()+80, getEfType()+","+MoveType.down));
			}
		}	
	}
	
	public void move(/*int time*/) {
		if (getY()<50) {
			setY(getY()+5);
		}else {
//			System.out.println(time);
			if(getX()<21)
				direction=1;
			else if(getX()>181)
				direction=-1;
			setX(getX()+5*direction);
		}
	}

	@Override
	public void destroy() {
		if(!isVisible())
		{
			List<SuperElement> list =
					ElementManager.getManager().getElementList("explosion");
			for(int i=0;i<50;i++)
			list.add(ElementFactory.elementFactory(getX()+i*10,getY()+50,"explosion"));	

		}	
	}
	
	public void paintHP(Graphics g) {
		g.drawRect((int) Math.round(getX()-25), (int) Math.round(getY()-12), 200, 10);
		g.setColor(Color.RED);
		g.fillRect((int) Math.round(getX()-25), (int) Math.round(getY()-12), getHp()/25, 10);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public PlayerFire getBossFire() {
		return bossFire;
	}

	public void setBossFire(PlayerFire bossFire) {
		this.bossFire = bossFire;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
