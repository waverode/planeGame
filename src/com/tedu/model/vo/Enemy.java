package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class Enemy extends SuperElement{
	private ImageIcon img;
	private String name;  //敌机种类
	private int hp;  //血量
	private String efType;  //敌机子弹种类
	private int score;		//分数
	private int beginY;  //飞机出现起点纵坐标
	private int beginX;	//飞机出现起点横坐标
	
	public Enemy(int x, int y, int w, int h,String efType,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.efType=efType;
		this.beginY=y;
		this.beginX=x;
		hp=5;
		score=5;
	}
	public static Enemy createEnemey(String url){
//		004=enemyA,enemyA,enemyFire01,20,170,40,40,10
		String []arr=url.split(",");
		int x=Integer.parseInt(arr[3]);
		int y=Integer.parseInt(arr[4]);
		int w=Integer.parseInt(arr[5]);
		int h=Integer.parseInt(arr[6]);
		String efType = arr[2];
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[1]);
		Enemy enemy=null;
		switch(arr[0]){//在配置文件中可以给与类型
		case "enemyMoveX":
			enemy=new EnemyA(x, y, w, h,efType,img);
			break;
		case "enemyMoveParabolic":
			enemy=new EnemyE(x, y, w, h, efType,img);
			break;
		case "enemyMoveSlash":
			enemy=new EnemyC(x, y, w, h, efType,img);
			break;
		default:
			enemy=new Enemy(x,y,w,h,efType,img);
		}
		return enemy/*new Enemy(x,y,w,h,img)*/;
	}
	
	public void addFire(int time) {
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
 
		if(time%40==0) {
//		2.左斜下
			if(beginX>320) {
			list.add(ElementFactory.elementFactory(getX()+15, getY()+20, getEfType()+","+MoveType.leftdown));	
			list.add(ElementFactory.elementFactory(getX(), getY()+30, getEfType()+","+MoveType.leftdown));
			list.add(ElementFactory.elementFactory(getX()-15, getY()+40, getEfType()+","+MoveType.leftdown));
			}
//		3.右斜下
			else if (beginX<160) {
			list.add(ElementFactory.elementFactory(getX()-15, getY()+20, getEfType()+","+MoveType.rightdown));	
			list.add(ElementFactory.elementFactory(getX(), getY()+30, getEfType()+","+MoveType.rightdown));
			list.add(ElementFactory.elementFactory(getX()+15, getY()+40, getEfType()+","+MoveType.rightdown));
			}
//		4.四散
			else if (beginX<=320 && beginX>=160) {
			list.add(ElementFactory.elementFactory(getX()+25, getY()+10, getEfType()+","+MoveType.top));	 
			list.add(ElementFactory.elementFactory(getX()+10, getY()+25, getEfType()+","+MoveType.left));	
			list.add(ElementFactory.elementFactory(getX()+25, getY()+65, getEfType()+","+MoveType.down));
			list.add(ElementFactory.elementFactory(getX()+65, getY()+25, getEfType()+","+MoveType.right));
			}
		}
//		5.6边集合弹
//		list.add(ElementFactory.elementFactory(getX()+10, getY()+45, getEfType()+","+MoveType.down));	 
//		list.add(ElementFactory.elementFactory(getX()+20, getY()+45, getEfType()+","+MoveType.down));	
//		list.add(ElementFactory.elementFactory(getX()+5, getY()+55, getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+15, getY()+55, getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+25, getY()+55, getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+10, getY()+65, getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+20, getY()+65, getEfType()+","+MoveType.down));

//		6.蛇形弹  需要time
//		list.add(ElementFactory.elementFactory(getX()-15, getY()+10, getEfType()+","+MoveType.leftdown));	
//		list.add(ElementFactory.elementFactory(getX(), getY(), getEfType()+","+MoveType.leftdown));
//		list.add(ElementFactory.elementFactory(getX()+15, getY()-10, getEfType()+","+MoveType.leftdown));
//		if(time%==10) {
//		list.add(ElementFactory.elementFactory(getX()+15, getY()+10, getEfType()+","+MoveType.rightdown));	
//		list.add(ElementFactory.elementFactory(getX(), getY(), getEfType()+","+MoveType.rightdown));
//		list.add(ElementFactory.elementFactory(getX()-15, getY()+10, getEfType()+","+MoveType.rightdown));
//		}if(time%==10) {
//		list.add(ElementFactory.elementFactory(getX()-15, getY()+10, getEfType()+","+MoveType.leftdown));	
//		list.add(ElementFactory.elementFactory(getX(), getY(), getEfType()+","+MoveType.leftdown));
//		list.add(ElementFactory.elementFactory(getX()+15, getY()-10, getEfType()+","+MoveType.leftdown));
//		}
		
//		7. 三角弹
//		list.add(ElementFactory.elementFactory(getX()+15, getY()+10, getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+10, getY()+5, getEfType()+","+MoveType.lefttop));
//		list.add(ElementFactory.elementFactory(getX()+20, getY()+5, getEfType()+","+MoveType.righttop));

//		boss 1. 4条子弹
//		list.add(ElementFactory.elementFactory(getX()-30, getY(), getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+20, getY(), getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+60, getY(), getEfType()+","+MoveType.down));
//		list.add(ElementFactory.elementFactory(getX()+110, getY(), getEfType()+","+MoveType.down));
	
//		boss 2. 圆圈四散
//		list.add(ElementFactory.elementFactory(getX()+15, getY(), getEfType()+","+MoveType.top));
//		list.add(ElementFactory.elementFactory(getX(), getY()+10, getEfType()+","+MoveType.lefttop));
//		list.add(ElementFactory.elementFactory(getX()+30, getY()+10, getEfType()+","+MoveType.righttop));
//		list.add(ElementFactory.elementFactory(getX()-10, getY()+20, getEfType()+","+MoveType.left));
//		list.add(ElementFactory.elementFactory(getX()+40, getY()+20, getEfType()+","+MoveType.right));
//		list.add(ElementFactory.elementFactory(getX(), getY()+30, getEfType()+","+MoveType.leftdown));
//		list.add(ElementFactory.elementFactory(getX()+30, getY()+30, getEfType()+","+MoveType.rightdown));
//		list.add(ElementFactory.elementFactory(getX()+15, getY()+40, getEfType()+","+MoveType.down));

	}
	
	@Override
	public void update(int time) {
		super.update(time);
			addFire( time);
	}
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(), getW(), getH(), null);
	}
	@Override
	public void move() {
		if(beginY<400) {//上边
			setY(getY()+3);
			//出界 销毁
			if(getY()>1000){
				this.setVisible(false);
			}	
		}else {//下边
			setY(getY()-3);
			if(getY()<-200){
				this.setVisible(false);
			}	
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		if(!isVisible() && (getX()<700 && getX()>-200 && getY()>-200 && getY()<1000))
		{	List<SuperElement> list =
			ElementManager.getManager().getElementList("explosion");
	list.add(ElementFactory.elementFactory(getX(),getY(),"explosion"));	
		}
	}
	
	
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getEfType() {
		return efType;
	}
	public void setEfType(String efType) {
		this.efType = efType;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	
}
