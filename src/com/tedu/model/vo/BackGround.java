package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class BackGround extends SuperElement{
    
	private ImageIcon img;
	
	private int step;   //天空移动速度
	
	
	public BackGround(int x, int y, int w, int h,ImageIcon img) {
		// TODO 自动生成的构造函数存根
		super(x, y, w, h);
		step=2;
		this.img=img;
	}
	
	public static BackGround createBackGround(String url) {
		String[] arr=url.split(",");
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=Integer.parseInt(arr[3]);
		int h=Integer.parseInt(arr[4]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		BackGround backGround=new BackGround(x, y, w, h, img);
		return backGround;
		
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img.getImage(), 
				getX(), getY(), getW(), getH(), null);
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		setY(getY()+step);
		if(getY()>=800) {
			setY(-800);
		}
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
    
    
    
}