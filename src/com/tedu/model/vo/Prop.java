package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class Prop extends SuperElement{
    private ImageIcon img;
    
    private  String propName; //道具名字

	public  String getPropName() {
		return propName;
	}

	public  void setPropName(String propName) {
		this.propName = propName;
	}

	public Prop(int x, int y, int w, int h,ImageIcon img,String name) {
		super(x, y, w, h);
		this.img=img;
		this.propName=name;
		// TODO 自动生成的构造函数存根
	}

	public static Prop createProp(String url) {
		String[] arr=url.split(",");
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=Integer.parseInt(arr[3]);
		int h=Integer.parseInt(arr[4]);
		//propName=arr[0];
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		Prop prop=null; 
		prop=new Prop(x, y, w, h, img,arr[0]);
		return prop;
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		setY(getY()+5);
		if(getY()>800) {
			this.setVisible(false);
		}
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		if(!isVisible()) {
			
		}
	}



}
