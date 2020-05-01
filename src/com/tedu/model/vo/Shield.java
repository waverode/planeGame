package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class Shield extends SuperElement{
    private ImageIcon imageIcon;
    
	public Shield() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public Shield(int x, int y, int w, int h, ImageIcon imageIcon) {
		super(x, y, w, h);
		this.imageIcon=imageIcon;
		// TODO 自动生成的构造函数存根
	}

	public static Shield createShield(int x,int y,String str) {
		String []arr=str.split(",");
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		int w=Integer.parseInt(arr[1]);
		int h=Integer.parseInt(arr[2]);
		return new Shield(x,y,w,h,img);
	}
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		
	    g.drawImage(imageIcon.getImage(),getX(),getY(),
	    		getW(),getH(),null);
	    setVisible(false);
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}


	@Override
	public void move() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

}
