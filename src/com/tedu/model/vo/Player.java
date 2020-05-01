package com.tedu.model.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manager.ElementFactory;
import com.tedu.model.manager.ElementManager;
import com.tedu.model.manager.MoveType;

public class Player extends SuperElement{
	private int hp;//血量
	private int mp;//能量
	private String name;//玩家名称，区分1p2p
	private ImageIcon img;//飞机图片
	private String FireType; //子弹类型
	private int level;	  //子弹等级，控制一次性发射几个子弹
//	当前玩家名称。。。。
	private MoveType moveType;//0 1 2 3
	private boolean  pk;//攻击状态，默认为 false
	private boolean useShield; //使用护盾，默认为false
	private int shieldTime; //护盾使用时间
	private boolean  useBigFire; //使用大招，默认为false
//   若飞机图片是动态时，控制图片切换	
	private int moveX;
	private int movey;
//   获得道具的提示时间
	private int tipLifeTime;
	private int tipLevelTime;
////   调整飞机可以斜着飞
//	int vx =0; 
//	int vy =-2;

	
	public Player(int x,int y,int w,int z,String FireType,ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则  
		hp=100;
		mp=100;
		level=1;
		shieldTime=0;
		tipLifeTime=0;
		tipLevelTime=0;
		this.FireType=FireType;
		moveType=MoveType.stop;
		pk=false;
		useBigFire=false;
		useShield=false;
//		new MoveObj(this).start();
	}
	//可以直接调用这个方法，用来得到一个玩家对象  str里面包含的就是玩家对象的信息
	public static Player createPlayer(String str){
		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);		
		return new Player(x,y,w,h,arr[1],img);
	}
	
	@Override
	public void showElement(Graphics g) {
		if(tipLevelTime>0) {
			g.setColor(Color.WHITE);
		    g.setFont(new Font("Times",Font.BOLD,20));
		    g.drawString("恭喜：子弹等级+1！！！", 10, 88);
		}
		if(tipLifeTime>0) {
			g.setColor(Color.WHITE);
		    g.setFont(new Font("Times",Font.BOLD,20));
		    g.drawString("恭喜：获得 生命值100！！！", 10, 88);
		}
		if(useShield){
			g.setColor(Color.WHITE);
		    g.setFont(new Font("Times",Font.BOLD,20));
		    g.drawString("护盾剩余时间："+(151-shieldTime)/10+"s", 10, 88);
		}
		g.drawImage(img.getImage(), 
			getX(), getY(),                  //屏幕左上角坐标
			getX()+getW(), getY()+getH(),    //屏幕右下角坐标
				60*moveX, 0,    //图片左上角坐标        60 ,0
				60*(moveX+1), 60,    //图片右下角坐标  120,60	
				null);
	}
	public void move(){
//		hp++;
		if(tipLevelTime>0) tipLevelTime--;
		if(tipLifeTime>0) tipLifeTime--;
	    switch(moveType) {
		case top:
			if(this.getY()<0)
			{break;}	
			else	
			{this.setY(getY()-18);break;}
		case left:
			if(this.getX()<0)
			{break;}	
			else 
			{this.setX(getX()-18);break;}
		case right:
			if(this.getX()>440)
			{break;}	
			else 
			{this.setX(getX()+18);break;}
		case down:
			if(this.getY()>700)
			{break;}	
			else	
			{this.setY(getY()+18);;break;}
		case stop: break;
		}		
	}
	
	public void NoHp() {
		if(getHp()<0) {
		List<SuperElement> list =
				ElementManager.getManager().getElementList("explosion");
		list.add(ElementFactory.elementFactory(getX(),getY(),"explosion"));	
		setVisible(false);
		}else
			return;
	}
	
//	重写父类的模板
	public void update(int time){
		super.update(time);//如果没有这句话，就是 重新制定新模板
		addFire();//追加
		addBigFire();
		addShield();
		updateImage();
		NoHp();
	}
	public void updateImage(){
		moveX=(moveX==0)?1:0;
//		做方向判定，改变 movex 和movey的值。
//		moveX=(moveX>=60)?0:moveX+1;
	}
	
	public void addShield() {
       if(useShield) {
    	   shieldTime++;
    	   if(shieldTime>150)
    		   {
    		   shieldTime=0;   
    		   useShield=false;
    		   }
		List<SuperElement> list =new ArrayList<>();
		list=ElementManager.getManager().getElementList("shield");
		list.add(ElementFactory.elementFactory(getX(),getY(),"shield"));	
       }
       else
    	   return;
    //;
    }
	
	public int getTipLifeTime() {
		return tipLifeTime;
	}
	public void setTipLifeTime(int tipLifeTime) {
		this.tipLifeTime = tipLifeTime;
	}
	public int getTipLevelTime() {
		return tipLevelTime;
	}
	public void setTipLevelTime(int tipLevelTime) {
		this.tipLevelTime = tipLevelTime;
	}
	public boolean isUseShield() {
		return useShield;
	}
	public void setUseShield(boolean useShield) {
		this.useShield = useShield;
	}
	public int getShieldTime() {
		return shieldTime;
	}
	public void setShieldTime(int shieldTime) {
		this.shieldTime = shieldTime;
	}
	public void addFire(){
// 	 有不同类型的子弹，在工厂创建	
		if(!pk){//如果PK是false就不需要 添加子弹
			return;
		}
		List<SuperElement> list=new ArrayList<>();
		switch (getLevel()) {
		case 1:
			list=ElementManager.getManager().getElementList("playFire");
	list.add(ElementFactory.elementFactory(getX(),getY(),getFireType()+","+MoveType.top));			
			break;
		case 2:
			list=ElementManager.getManager().getElementList("playFire");
	list.add(ElementFactory.elementFactory(getX(),getY(),getFireType()+","+MoveType.top));			
	list.add(ElementFactory.elementFactory(getX()+30,getY(),getFireType()+","+MoveType.top));					
			break;	
		case 3:
			list=ElementManager.getManager().getElementList("playFire");
	list.add(ElementFactory.elementFactory(getX(),getY(),getFireType()+","+MoveType.lefttop));			
	list.add(ElementFactory.elementFactory(getX()+15,getY(),getFireType()+","+MoveType.top));					
	list.add(ElementFactory.elementFactory(getX()+40,getY(),getFireType()+","+MoveType.righttop));
			break;
		}
//			pk=false;//每按一次 只能发射一次子弹
	}
	
	public void addBigFire(){
		List<SuperElement> powerlist=ElementManager.getManager().getElementList("power");
		Power power=(Power)powerlist.get(0);

		if(!useBigFire || power.getPower()<450) {//如果PK是false就不需要 放大招
			return;
		}
		List<SuperElement> list=
				ElementManager.getManager().getElementList("playFire");
		list.add(ElementFactory.elementFactory(getX()-100,getY(),"BigFire01"));
		power.setPower(power.getPower()-450);
		useBigFire=false;
	}

	public MoveType getMoveType() {
		return moveType;
	}
	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		if(getMp()+mp<=100)
		this.mp = mp;
		else
			this.mp=100;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isUseBigFire() {
		return useBigFire;
	}
	public void setUseBigFire(boolean useBigFire) {
		this.useBigFire = useBigFire;
	}
	
	public String getFireType() {
		return FireType;
	}
	public void setFireType(String fireType) {
		FireType = fireType;
	}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
