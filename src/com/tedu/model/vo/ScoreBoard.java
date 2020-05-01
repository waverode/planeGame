package com.tedu.model.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.tedu.model.vo.SuperElement;

public class ScoreBoard extends SuperElement{
	private int score;
	private static ImageIcon[] img={new ImageIcon("img/score/score.png"),new ImageIcon("img/score/0.png"),
				new ImageIcon("img/score/0.png"),new ImageIcon("img/score/0.png"),
				new ImageIcon("img/score/0.png"),new ImageIcon("img/score/0.png")};
	
	public ScoreBoard(int x, int y, int w, int h) {
		super(x, y, w, h);
		score=0;
	}

	@Override
	public void showElement(Graphics g) {
//		g.setColor(Color.WHITE);
//		g.setFont(new Font("Times", Font.BOLD, 15));
//		g.drawString("SCORE: " + Player.getScore(), 10, 25);
		g.drawImage(img[0].getImage(), getX(), getY(), 60, getH(), null);//score:
		g.drawImage(img[1].getImage(), getX()+60, getY(), 10, getH(), null);
		g.drawImage(img[2].getImage(), getX()+70, getY(), 10, getH(), null);
		g.drawImage(img[3].getImage(), getX()+80, getY(), 10, getH(), null);
		g.drawImage(img[4].getImage(), getX()+90, getY(), 10, getH(), null);
		g.drawImage(img[5].getImage(), getX()+100, getY(), 10, getH(), null);
	}

	@Override
	public void move() {//改变图片
		int sc=getScore();
		for(int i=5;i>=1;i--) {			
			
			int temp=sc%10;
			String str="img/score/"+temp+".png";
			sc=sc/10;
			img[i].setImage(new ImageIcon(str).getImage());  //更改图片
		}
			
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

}
