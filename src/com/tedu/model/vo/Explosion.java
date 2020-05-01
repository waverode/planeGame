package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Explosion extends SuperElement{
	private ImageIcon img;  //爆炸图片，应该是动态的图片
	//作用于图片切换
	private int moveX; 
	private int moveY; 
	
	public static Explosion createExplosin(int x,int y,String str) {
		return new Explosion(x,y,100,100,new ImageIcon(str));
		
	}
	
	
	public Explosion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Explosion(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		moveX=0;
	}



	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(),
					getX(),getY() , 
					getX()+getW(),getY()+getH(), 
					65*moveX,0,							//0  0    65  0
					65*(moveX+1), 66, 					//65 66	 130  66 
					null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(int time) {
		// TODO Auto-generated method stub
		super.update(time);
		updateImage();
	}

	private void updateImage() {
		if(moveX==8)
			setVisible(false);
		moveX++;
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
