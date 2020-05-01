package com.tedu.model.vo;

import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class EnemyA extends Enemy{//�������
	
	private int hp;
	private int score;
	
	private int beginX;  //�ɻ�������������
	
	public EnemyA(int x, int y, int w, int h, String efType, ImageIcon img) {
		super(x, y, w, h, efType, img);
		beginX=x;
		score=10;
		hp=10;
	}
	
 @Override
	public void addFire(int time) {
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
 
		if(time%30==0) {
			int random = (int)(Math.random()*100);
			if(random<50) {
//			1.ֱ��				
				list.add(ElementFactory.elementFactory(getX()+25, getY()+20, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+25, getY(), getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+25, getY()-20, getEfType()+","+MoveType.down));
			}
			else if(random>60){
//				5.6�߼��ϵ�
				list.add(ElementFactory.elementFactory(getX()+10, getY()+45, getEfType()+","+MoveType.down));	 
				list.add(ElementFactory.elementFactory(getX()+30, getY()+45, getEfType()+","+MoveType.down));	
				list.add(ElementFactory.elementFactory(getX(), getY()+65, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+20, getY()+65, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+40, getY()+65, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+10, getY()+85, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+30, getY()+85, getEfType()+","+MoveType.down));
			}
			else {
//				6.���ε�  
				list.add(ElementFactory.elementFactory(getX()+15, getY()-20, getEfType()+","+MoveType.down));	
				list.add(ElementFactory.elementFactory(getX(), getY(), getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()-15, getY()+20, getEfType()+","+MoveType.down));				
				
				list.add(ElementFactory.elementFactory(getX()-15, getY()+30, getEfType()+","+MoveType.down));	
				list.add(ElementFactory.elementFactory(getX(), getY()+50, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()+15, getY()+70, getEfType()+","+MoveType.down));		
				
				list.add(ElementFactory.elementFactory(getX()+15, getY()+80, getEfType()+","+MoveType.down));	
				list.add(ElementFactory.elementFactory(getX(), getY()+100, getEfType()+","+MoveType.down));
				list.add(ElementFactory.elementFactory(getX()-15, getY()+120, getEfType()+","+MoveType.down));
				
			}
		}
			
	}
	
	@Override
	public void move() {
		if(beginX<250) {//���
			setX(getX()+3);
			//���� ����
			if(getX()>700){
				this.setVisible(false);
			}	
		}else {//�ұ�
			setX(getX()-5);
			if(getX()<-200){
				this.setVisible(false);
			}	
		}
	}
	
}
