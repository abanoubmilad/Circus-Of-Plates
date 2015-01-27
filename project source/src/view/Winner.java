/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Winner extends JFrame {

	private static final long serialVersionUID = -620355192622296680L;

	private static Winner winner = new Winner();

	// private PlayerObserver firstPlayerObserver,secondPlayerObserver;
	public static Winner getInstance() {
		return winner;
	}

	private JLabel winnerLabel, bgLabel;
	private int width, height;

	private Winner() {
	}

	public void set(String name) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth() - 100;
		height = (int) screenSize.getHeight() - 100;

		width /= 3;
		height /= 2;

		winnerLabel = new JLabel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, width, height);
		setTitle("Winner " + name + " !!!");
		setLayout(null);
		setResizable(false);

		bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, width, height);
		bgLabel.setIcon(ImageScaler.getInstance().scaleImage(width, height,
				"/pics/bg.jpg"));

		Font font = new Font("Serif", Font.PLAIN, 40);

		winnerLabel.setForeground(Color.white);
		winnerLabel.setFont(font);
		winnerLabel.setBounds(100, 50, width, height);

		winnerLabel.setText("<html><center>Winner <br>" + name
				+ "!!!</center></html>");

		getContentPane().add(winnerLabel);
		getContentPane().add(bgLabel);
		setVisible(true);
	}
}