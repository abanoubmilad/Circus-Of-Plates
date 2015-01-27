/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */

import java.awt.EventQueue;

import model.Sound;
import view.CircusView;
import control.KeyListner;
import control.OperatingCircus;


public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sound.getInstance().startCircusSound();

					CircusView circus = CircusView.getInstance();
					circus.setVisible(true);
					
					circus.addKeyListener(new KeyListner());
					
					Thread thread = new Thread(OperatingCircus.getInstance());
					thread.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
