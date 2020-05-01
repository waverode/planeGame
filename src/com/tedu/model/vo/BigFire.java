package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.MoveType;

public class BigFire extends PlayerFire{
	private ImageIcon img;	
	private int power;     //大招的威力
	
	
	public BigFire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BigFire(int x, int y, int w, int h,int power,ImageIcon img) {
		super(x, y, w, h, power, MoveType.top, img);
		this.power=power;
		this.img=img;
	}
	
	public static BigFire createBigFire(int x,int y,String str) {
		//BigFire01=bigFireA,50,50,50
		String []arr=str.split(",");
		ImageIcon image = ElementLoad.getElementLoad().getMap().get(arr[0]);
		int power = Integer.parseInt(arr[1]) ;
		int w = Integer.parseInt(arr[2]);
		int h = Integer.parseInt(arr[3]);
		return new BigFire(x, y, w, h, power, image);
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		setY(getY()-30);
//		超过边界的 是不是要 销毁
		if(getY()<-100){
			setVisible(false);
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
