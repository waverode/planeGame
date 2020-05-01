package com.tedu.model.vo;

import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class EnemyC extends Enemy{//�ƶ�  б��

	private int hp;
	private int score;
	
	private int beginX;  //�ɻ�������������
	private int beginY;  //�ɻ��������������
	
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
//				1.ֱ�� 
				list.add(ElementFactory.elementFactory(getX()+25, getY()+20, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+25, getY(), getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+25, getY()-20, getEfType()+","+MoveType.down));
			}else {
//				4.��ɢ
				list.add(ElementFactory.elementFactory(getX()+25, getY()+10, getEfType()+","+MoveType.top));	 
				list.add(ElementFactory.elementFactory(getX()+10, getY()+25, getEfType()+","+MoveType.left));	
				list.add(ElementFactory.elementFactory(getX()+25, getY()+65, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+65, getY()+25, getEfType()+","+MoveType.right));
			}
		}
	}
	
	
	
	@Override
	public void move() {
		if(beginX>250 && beginY<400) {//����
			setX(getX()-3);
			setY(getY()+3);
			//���磬����	
			if(getX()<-200 || getY()>1000){
				this.setVisible(false);
			}
		}else if (beginX<250 && beginY<400) {//����
			setX(getX()+3);
			setY(getY()+3);
				
			if(getX()>700 || getY()>1000){
				this.setVisible(false);
			}	
		}else if (beginX<250 && beginY>400) {//����
			setX(getX()+3);
			setY(getY()-3);
				
			if(getX()>700 && getY()<-200){
				this.setVisible(false);
			}
		}else {//����
			setX(getX()-3);
			setY(getY()-3);
				
			if(getX()<-200 && getY()<-200){
				this.setVisible(false);
			}
		}
		
	}

}
