package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class BackGround1 extends SuperElement{

	private ImageIcon img;
	
	private int step;   //����ƶ��ٶ�
	
	
	public BackGround1(int x, int y, int w, int h,ImageIcon img) {
		// TODO �Զ����ɵĹ��캯�����
		super(x, y, w, h);
		step=2;
		this.img=img;
	}
	public static BackGround1 createBackGround1(String url) {
		String[] arr=url.split(",");
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=Integer.parseInt(arr[3]);
		int h=Integer.parseInt(arr[4]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		BackGround1 backGround1=new BackGround1(x, y, w, h, img);
		return backGround1;
		
	}
	@Override
	public void showElement(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img.getImage(), 
				getX(), getY(), getW(), getH(), null);
	}

	@Override
	public void move() {
		// TODO �Զ����ɵķ������
		setY(getY()+step);
		if(getY()>=800) {
			setY(-800);
		}
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
}
