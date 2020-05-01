package com.tedu.model.vo;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.MoveType;

public class BossFire extends EnemyFire{
	private int flag;
	
	
	public BossFire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BossFire(int x, int y, int w, int h, int power, MoveType moveType, ImageIcon img) {
		super(x, y, w, h, power, moveType, img);
		flag=0;
		// TODO Auto-generated constructor stub
	}

	public static BossFire createBossFire(int x,int y,String str) {
//		enemyFireA,2,10,10
		String []arr=str.split(",");
		int w = Integer.parseInt(arr[2]);
		int h = Integer.parseInt(arr[3]);
		int power = Integer.parseInt(arr[1]);
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);
		switch (arr[4]) {
		case "top":
			return new BossFire(x,y,w,h,power,MoveType.top,img);
		case "left":
			return new BossFire(x,y,w,h,power,MoveType.left,img);
		case "right":
			return new BossFire(x,y,w,h,power,MoveType.right,img);
		case "down":
			return new BossFire(x,y,w,h,power,MoveType.down,img);
		case "leftdown":
			return new BossFire(x,y,w,h,power,MoveType.leftdown,img);
		case "rightdown":
			return new BossFire(x,y,w,h,power,MoveType.rightdown,img);
		case "righttop":
			return new BossFire(x,y,w,h,power,MoveType.righttop,img);
		case "lefttop":
			return new BossFire(x,y,w,h,power,MoveType.lefttop,img);	
		default: return new BossFire(x,y,w,h,power,MoveType.down,img);
		}
	}
	
	public void move() {
			switch(this.getMoveType()){
			case top:if(getY()<400 && flag==0) {
				setY(getY()+10);break;
			}
			else {
				setY(getY()-10); flag=1; break;	}	
			case left:if(getY()<440 && flag==0) {
				setY(getY()+10); break;
			}else
				{setX(getX()-10); flag=1;break;}
			case right:if(getY()<440 && flag==0) {
				setY(getY()+10);break;
			}else
				{setX(getX()+10); flag=1; break;}
			case down:if(getY()<480 && flag==0) {
				setY(getY()+10);break;
			}else
				{setY(getY()+10); flag=1;  break;}
			case leftdown:if(getY()<460 && flag==0) {
				setY(getY()+10);break;
			}else
				{setY(getY()+7); setX(getX()-7); flag=1; break;}
			case rightdown:if(getY()<460 && flag==0) {
				setY(getY()+10); break;
			}else
				{setY(getY()+7); setX(getX()+7); flag=1;break;}
			case righttop:if(getY()<420 && flag==0) {
				setY(getY()+10);break;
			}else
				{setY(getY()-7); setX(getX()+7); flag=1; break;}
			case lefttop:if(getY()<420 && flag==0) {
				setY(getY()+10);break;
			}else
				{setY(getY()-7); setX(getX()-7); flag=1; break;}
			}
	
	
//	超过边界的 是不是要 销毁
		if(getY()>800 || getX()<0 || getX()>500){
		setVisible(false);
	}
	}
}
