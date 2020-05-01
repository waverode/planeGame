package com.tedu.model.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Paint;

public class Power extends SuperElement{
    private int power=0;
    private int tipTime=0;   //��û�����ʾʱ��
	public Power() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	public Power(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public void upPower() {
		
	}
	@Override
	public void showElement(Graphics g) {
		// TODO �Զ����ɵķ������
		if(power<=(180*5))
			power++;
		if(tipTime>0)
			g.drawString("��ϲ���������ֵ500������", 10, 88);
		g.drawRect((int) Math.round(57), (int) Math.round(44), 180, 10);
		g.setColor(Color.YELLOW);
		g.fillRect((int) Math.round(57), (int) Math.round(44), power /5, 10);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times",Font.BOLD,20));
		g.drawString("MP��", 10, 55);
		
	}
	
    public int getTipTime() {
		return tipTime;
	}
	public void setTipTime(int tipTime) {
		this.tipTime = tipTime;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		if(power<=900)
		this.power = power;
		else
			this.power=900;
	}
	public static Power createPower() {
		// TODO �Զ����ɵĹ��캯�����
    	
    	return new Power(1,1,1,1);
	}
	@Override
	public void move() {
		// TODO �Զ����ɵķ������
		if(tipTime>0) {
			tipTime--;
		}
	}
    
	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		
	}
}