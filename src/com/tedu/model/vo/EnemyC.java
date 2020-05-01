package com.tedu.model.vo;

import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class EnemyC extends Enemy{//移动  斜线

	private int hp;
	private int score;
	
	private int beginX;  //飞机出现起点横坐标
	private int beginY;  //飞机出现起点纵坐标
	
	public EnemyC(int x, int y, int w, int h, String efType, ImageIcon img) {
		super(x, y, w, h, efType, img);
		beginX=x;
		beginY=y;
		score=15;
		hp=15;
	}
	
	@Override
	public void addFire(int time) {
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
		if(time%30==0) {
		int random = (int)(Math.random()*100);
			if(random>30) {
//				1.直下 
				list.add(ElementFactory.elementFactory(getX()+25, getY()+20, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+25, getY(), getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+25, getY()-20, getEfType()+","+MoveType.down));
			}else {
//				4.四散
				list.add(ElementFactory.elementFactory(getX()+25, getY()+10, getEfType()+","+MoveType.top));	 
				list.add(ElementFactory.elementFactory(getX()+10, getY()+25, getEfType()+","+MoveType.left));	
				list.add(ElementFactory.elementFactory(getX()+25, getY()+65, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+65, getY()+25, getEfType()+","+MoveType.right));
			}
		}
	}
	
	
	
	@Override
	public void move() {
		if(beginX>250 && beginY<400) {//右上
			setX(getX()-3);
			setY(getY()+3);
			//出界，销毁	
			if(getX()<-200 || getY()>1000){
				this.setVisible(false);
			}
		}else if (beginX<250 && beginY<400) {//左上
			setX(getX()+3);
			setY(getY()+3);
				
			if(getX()>700 || getY()>1000){
				this.setVisible(false);
			}	
		}else if (beginX<250 && beginY>400) {//左下
			setX(getX()+3);
			setY(getY()-3);
				
			if(getX()>700 && getY()<-200){
				this.setVisible(false);
			}
		}else {//右下
			setX(getX()-3);
			setY(getY()-3);
				
			if(getX()<-200 && getY()<-200){
				this.setVisible(false);
			}
		}
		
	}

}
