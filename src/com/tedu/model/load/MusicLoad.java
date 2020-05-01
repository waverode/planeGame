package com.tedu.model.load;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

import javax.swing.JFrame;

public class MusicLoad extends JFrame{
    public static AudioClip backgroundmusic; //±≥æ∞“Ù¿÷
    
    //æ≤Ã¨øÈ£¨º”‘ÿ±≥æ∞“Ù¿÷
    static {
    	try{
    		backgroundmusic=Applet.newAudioClip(
                    new File("music/backgroundmusic.wav")
                    .toURI().toURL());
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    
    public void action() {
    	backgroundmusic.loop();
    }
}
