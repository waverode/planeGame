package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.MoveType;

public class EnemyFire extends SuperElement{
	private ImageIcon img;
	private int power;     //子弹的威力
	private MoveType moveType; //子弹移动方向
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);
	}

	public EnemyFire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnemyFire(int x, int y, int w, int h,int power,MoveType moveType,ImageIcon img) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		this.img=img;
		this.power=power;
		this.moveType =moveType;
	}

	public static EnemyFire createEnemyFire(int x,int y,String str) {
//		enemyFireA,2,10,10
		String []arr=str.split(",");
		int w = Integer.parseInt(arr[2]);
		int h = Integer.parseInt(arr[3]);
		int power = Integer.parseInt(arr[1]);
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);
		switch (arr[4]) {
		case "top":
			return new EnemyFire(x,y,w,h,power,MoveType.top,img);
		case "left":
			return new EnemyFire(x,y,w,h,power,MoveType.left,img);
		case "right":
			return new EnemyFire(x,y,w,h,power,MoveType.right,img);
		case "down":
			return new EnemyFire(x,y,w,h,power,MoveType.down,img);
		case "leftdown":
			return new EnemyFire(x,y,w,h,power,MoveType.leftdown,img);
		case "rightdown":
			return new EnemyFire(x,y,w,h,power,MoveType.rightdown,img);
		case "righttop":
			return new EnemyFire(x,y,w,h,power,MoveType.righttop,img);
		case "lefttop":
			return new EnemyFire(x,y,w,h,power,MoveType.lefttop,img);	
		default: return new EnemyFire(x,y,w,h,power,MoveType.down,img);
		}
	}
	
	@Override
	public void move() {
		switch(moveType){
		case top:setY(getY()-10); break;		
		case left:setX(getX()-10);break;
		case right:setX(getX()+10);break;
		case down:setY(getY()+10); break;
		case leftdown:setY(getY()+7); setX(getX()-7);break;
		case rightdown:setY(getY()+7); setX(getX()+7);break;
		case righttop:setY(getY()-7); setX(getX()+7);break;
		case lefttop:setY(getY()-7); setX(getX()-7);break;
		case stop:
		}
//	超过边界的 是不是要 销毁
		if(getY()>800 || getX()<0 || getX()>500){
		setVisible(false);
	}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
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

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

}
