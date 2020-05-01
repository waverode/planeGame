package com.tedu.model.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.tedu.model.manager.ElementManager;

public class PlayerLife extends SuperElement{
    private int hp;
    
    
    
	public PlayerLife() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public PlayerLife(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		// TODO 自动生成的构造函数存根
	}
    
	public static PlayerLife createPlayerLife() {
		return new PlayerLife(10,25,10,10);
	}
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times",Font.BOLD,20));
		g.drawString("HP："+hp, 10, 25);
	}

	@Override
	public void move() {
		// TODO 自动生成的方法存根
		if(!ElementManager.getManager().getMap().get("play").isEmpty())
		{Player player=(Player)(ElementManager.getManager().getMap().get("play").get(0));
		hp=player.getHp();}
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}
   /* public void paint(Graphics g) {
    	
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("Times", Font.BOLD, 15));
    	g.drawString("HP：", 10, 25);
    }*/
}
