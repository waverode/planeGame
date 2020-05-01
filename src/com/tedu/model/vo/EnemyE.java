package com.tedu.model.vo;

import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class EnemyE extends Enemy{//�ƶ� ������

	private int hp;
	private int score;
	
	private int borderX; //������ת�۵������
	private int borderY; //������ת�۵�������
	private int beginX;  //�ɻ�������������
	private int beginY;  //�ɻ��������������
	
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
//			7. ���ǵ�
			list.add(ElementFactory.elementFactory(getX()+25, getY()+80, getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+5, getY()+60, getEfType()+","+MoveType.lefttop));
			list.add(ElementFactory.elementFactory(getX()+45, getY()+60, getEfType()+","+MoveType.righttop));
		}else {
//			4.��ɢ
			list.add(ElementFactory.elementFactory(getX()+25, getY()+10, getEfType()+","+MoveType.top));	 
			list.add(ElementFactory.elementFactory(getX()+10, getY()+25, getEfType()+","+MoveType.left));	
			list.add(ElementFactory.elementFactory(getX()+25, getY()+65, getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+65, getY()+25, getEfType()+","+MoveType.right));
		}
	}
}

	
	public void move() {
		if(beginX>250 && beginY<400) {//����
			if(getX()>borderX && getY()<borderY) {
				setX(getX()-3);
				setY(getY()+5);
			}else {
				setX(getX()-3);
				setY(getY()-5);
			}
			//������Ļ������
			if(getX()<-200 || getY()<-200) {
				this.setVisible(false);
			}
		}else if (beginX<250 && beginY<400) {//����
			if(getX()<=borderX && getY()<=borderY) {
				setX(getX()+3);
				setY(getY()+5);
			}else {
				setX(getX()+3);
				System.out.println(getX()+"+"+getY());
				setY(getY()-5);
			}
			//������Ļ������
			if(getX()>700 || getY()<-200) {
				this.setVisible(false);
			}
		}else if (beginX<250 && beginY>400) {//����
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
		}else {//����
			if(getX()>borderX && getY()>borderY) {
				setX(getX()-3);
				setY(getY()-5);
			}else {
				setX(getX()-3);
				setY(getY()+5);
			}
			//������Ļ������
			if(getX()<-200 || getY()>1000) {
				this.setVisible(false);
			}
		}
		
	}
}
