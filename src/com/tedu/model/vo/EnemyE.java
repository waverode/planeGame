package com.tedu.model.vo;

import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class EnemyE extends Enemy{//移动 抛物线

	private int hp;
	private int score;
	
	private int borderX; //抛物线转折点横坐标
	private int borderY; //抛物线转折点纵坐标
	private int beginX;  //飞机出现起点横坐标
	private int beginY;  //飞机出现起点纵坐标
	
	public EnemyE(int x, int y, int w, int h, String efType, ImageIcon img) {
		super(x, y, w, h, efType, img);
		borderX=20+(int)(Math.random()*240);
		borderY=120+(int)(Math.random()*200);
		beginX=x;
		beginY=y;
		score=20;
		hp=20;
	}

	@Override
	public void addFire(int time) {
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
		if(time%30==0) {
		int random = (int)(Math.random()*100);
		if(random>10) {
//			7. 三角弹
			list.add(ElementFactory.elementFactory(getX()+25, getY()+80, getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+5, getY()+60, getEfType()+","+MoveType.lefttop));
			list.add(ElementFactory.elementFactory(getX()+45, getY()+60, getEfType()+","+MoveType.righttop));
		}else {
//			4.四散
			list.add(ElementFactory.elementFactory(getX()+25, getY()+10, getEfType()+","+MoveType.top));	 
			list.add(ElementFactory.elementFactory(getX()+10, getY()+25, getEfType()+","+MoveType.left));	
			list.add(ElementFactory.elementFactory(getX()+25, getY()+65, getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+65, getY()+25, getEfType()+","+MoveType.right));
		}
	}
}

	
	public void move() {
		if(beginX>250 && beginY<400) {//右上
			if(getX()>borderX && getY()<borderY) {
				setX(getX()-3);
				setY(getY()+5);
			}else {
				setX(getX()-3);
				setY(getY()-5);
			}
			//超出屏幕，销毁
			if(getX()<-200 || getY()<-200) {
				this.setVisible(false);
			}
		}else if (beginX<250 && beginY<400) {//左上
			if(getX()<=borderX && getY()<=borderY) {
				setX(getX()+3);
				setY(getY()+5);
			}else {
				setX(getX()+3);
				System.out.println(getX()+"+"+getY());
				setY(getY()-5);
			}
			//超出屏幕，销毁
			if(getX()>700 || getY()<-200) {
				this.setVisible(false);
			}
		}else if (beginX<250 && beginY>400) {//左下
			if(getX()<borderX && getY()>borderY) {
				setX(getX()+3);
				setY(getY()-5);
			}else {
				setX(getX()+3);
				setY(getY()+5);
			}
			if(getX()>700 || getY()>1000) {
				this.setVisible(false);
			}	
		}else {//右下
			if(getX()>borderX && getY()>borderY) {
				setX(getX()-3);
				setY(getY()-5);
			}else {
				setX(getX()-3);
				setY(getY()+5);
			}
			//超出屏幕，销毁
			if(getX()<-200 || getY()>1000) {
				this.setVisible(false);
			}
		}
		
	}
}
