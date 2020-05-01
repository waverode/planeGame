package com.tedu.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tedu.model.manager.ElementManager;
import com.tedu.model.vo.SuperElement;
import com.tedu.thread.GameThread;

public class MyJPanel extends JPanel implements Runnable{
	private int time=0;
	//������Ϸ��ʼ
	private  boolean flag=false;
	//������Ϸ����
	private static boolean dead=false;
	/**
	 * 1.���paint���� �ײ��Զ����õģ�������д����ķ���
	 * 2.�������ֻ��ִ��1�Σ�������������ã��Ͳ������ִ��
	 * 
	 * ֡: 50-100����ÿ֡    20-10֡/��
	 */
	  //������ ������ʾ
	public void paint(Graphics g) {
		
		super.paint(g);
		this.setBackground(Color.black);
//		��һ���ж�ֵ  Ҳ����ʹ��ö��
//		1.ǰ����  pregametime
		pregameTime(g); 
//		2.gameRuntime
		if(flag==true)
		gameRunTime(g);//Graphics ����
//		3.�νӶ���
		if(dead)
		gameOver(g);
		time++;
	}

	private void gameOver(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(new ImageIcon("img/gameover.png").getImage(), 0, 0, 500, 800, null);
	}
	

	public static boolean isDead() {
		return dead;
	}

	public static void setDead(boolean dead) {
		MyJPanel.dead = dead;
	}


	private void pregameTime(Graphics g) {
		// TODO Auto-generated method stub
		if(flag==true) {
		this.removeAll();
		return ;
		}
		ImageIcon img = new ImageIcon("img/qian.gif");
		g.drawImage(img.getImage(), 0, 0, 500, 800, null);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				flag=true;

			}
		});
	}

	private void gameRunTime(Graphics g) {
//		List<SuperElement> list=
//				ElementManager.getManager().getElementList("XX");
//		g.drawString("*", 100, 100);
//		if(time>20) {
		Map<String,List<SuperElement>> map=
				ElementManager.getManager().getMap();
		Set<String> set=map.keySet();
		List<String> list=new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);//��Ȼ˳��
//		Collections.sort(list,"�Ƚ�������");//�Զ���˳��
//		֪ʶ�㣺java���϶����������� ��2���ӿ��й�
		for(String key:list){
			List<SuperElement> list1=map.get(key);
			for(int i=0;i<list1.size();i++){
				list1.get(i).showElement(g);
			}
		}
//	}
	}
	
	/**
	 * ʲô����д��
	 * 1.���м̳й�ϵ�� ������֮����﷨����(��̬��һ��ʵ��)
	 * 2.��д�ķ�������� ����ķ�����ǩ��һ��(����ֵ���������ƣ���������)
	 * 3.��д�ķ����������η�ֻ���Աȸ���ĸ��ӿ��ţ������Աȸ�����ӷ��
	 * 4.��д�ķ����׳��쳣�����Ա� ����ĸ��ӿ���
	 */
	@Override
	public void run(){
		while(true){//��ѭ��:����᲻ֹͣ��ˢ��
//			�̵߳�����
			try {
				Thread.sleep(100);//����
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.repaint();//Ҫ�� ����ٴ�ˢ��
		}
	}
	
	
	
	
}
