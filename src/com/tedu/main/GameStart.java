package com.tedu.main;

import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.model.load.MusicLoad;
import com.tedu.thread.GameListener;
/**
 * ��������� �Լ��������Լ���-����ȷ�ķֹ�
 * 
 */
public class GameStart {
//	������Ϸ����ڣ�����
	public static void main(String[] args) {
//		��Դ����
//		������أ��Զ���������
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		MusicLoad ml=new MusicLoad();
		GameListener listener=new GameListener();
		jf.setKeyListener(listener);
		jf.setJp(jp);//ע��
//		��������
		jf.addListener();
		jf.addJPanels();
		jf.addJPanels();//����jp
//      ���ּ���
		ml.action();
//		��Ϸ��������ʼ��
		jf.start();

	}

}
