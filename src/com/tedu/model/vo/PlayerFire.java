package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.MoveType;

public class PlayerFire extends SuperElement{
	private ImageIcon img;
	private int power;     //子弹的威力
	private MoveType moveType; //子弹移动方向
	
	public PlayerFire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayerFire(int x, int y, int w, int h,int power,MoveType moveType,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.power=power;
		this.moveType =moveType;
	}

	public static PlayerFire createPlayerFire(int x,int y,String str){
//		playFire01=playFireA,3,10,10,MoveType
		String []arr=str.split(",");
		ImageIcon img=ElementLoad.getElementLoad().getMap().get(arr[0]);
		int power =Integer.parseInt(arr[1]);
		int w =Integer.parseInt(arr[2]);
		int h =Integer.parseInt(arr[3]);
		switch (arr[4]) {
		case "top":
			return new PlayerFire(x,y,w,h,power,MoveType.top,img);
		case "lefttop":
			return new PlayerFire(x,y,w,h,power,MoveType.lefttop,img);
		case "righttop":
			return new PlayerFire(x,y,w,h,power,MoveType.righttop,img);
		case "down":
			return new PlayerFire(x,y,w,h,power,MoveType.down,img);
			default: return new PlayerFire(x,y,w,h,power,MoveType.top,img);
		}
		
	}
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);
	}
	@Override
	public void move() {
		switch(moveType){
		case top: setY(getY()-15);break;
		case lefttop:setY(getY()-15);setX(getX()-7);break;
		case righttop:setY(getY()-15);setX(getX()+7);break;
		case down:setY(getY()+15);break;
		case stop:
		}
//	超过边界的 是不是要 销毁
		if(getY()<0 || getX()<0 || getX()>500){
		setVisible(false);
	}
	}
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	

	@Override
	public void destroy() {

	}
}
