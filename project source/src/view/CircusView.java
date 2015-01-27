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
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.PlayerPublisher;
import control.Publishers;
import control.ValuePublisher;

import model.CircusInfo;
import model.Sound;

public class CircusView extends JFrame {

	private static final long serialVersionUID = -620355192622296680L;

	private static CircusView circus = new CircusView();

	// private PlayerObserver firstPlayerObserver,secondPlayerObserver;
	public static CircusView getInstance() {
		return circus;
	}

	private JLabel firstClown, secondClown, bgLabel;
	private JPanel drawPanel;
	private int width, height;
	private final int clownWidth = 140, clownheight = 180;
	private JLabel score1, score2, time;

	private CircusView() {
		initialize();
	}

	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth() - 100;
		height = (int) screenSize.getHeight() - 100;

		CircusInfo circusInfo = CircusInfo.getInstance();
		circusInfo.setCircusWidth(width);
		circusInfo.setCircusHeight(height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		setTitle("Circus of Plates");
		setLayout(null);
		setResizable(false);

		bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, width, height);
		bgLabel.setIcon(ImageScaler.getInstance().scaleImage(width, height,
				"/pics/bg.jpg"));

		drawPanel = PlatesView.getInstance();
		drawPanel.setBounds(0, 0, width, height);
		drawPanel.setOpaque(false);

		firstClown = new JLabel();
		secondClown = new JLabel();

		firstClown.setIcon(new ImageIcon(CircusView.class
				.getResource("/pics/fc.gif")));
		secondClown.setIcon(new ImageIcon(CircusView.class
				.getResource("/pics/sc.gif")));

		score1 = new JLabel();
		score2 = new JLabel();
		time = new JLabel();

		Font font = new Font("Serif", Font.PLAIN, 30);

		// <br><br>P1<br>
		firstClown
				.setText("<html><br><br><br>&nbsp &nbsp &nbsp &nbsp &nbsp p1<br>◂◀ &nbsp &nbsp  ▶▸</html>");
		firstClown.setForeground(Color.white);
		firstClown.setFont(font);
		firstClown.setIconTextGap(-clownWidth);
		//
		secondClown
				.setText("<html><br><br><br>&nbsp &nbsp &nbsp &nbsp &nbsp p2<br> ◂ A &nbsp D ▸</html>");
		secondClown.setForeground(Color.white);
		secondClown.setFont(font);
		secondClown.setIconTextGap(-clownWidth);

		font = new Font("Serif", Font.PLAIN, 40);

		// secondClown.setOpaque(false);
		// firstClown.setBounds(width / 2 - 250, height / 2, 300, 120);

		score1.setForeground(Color.ORANGE);
		score2.setForeground(Color.ORANGE);
		time.setForeground(Color.WHITE);

		score1.setFont(font);
		score2.setFont(font);
		time.setFont(font);

		score1.setOpaque(false);
		score2.setOpaque(false);
		time.setOpaque(false);

		score2.setBounds(width / 2 - 250, height / 2, 300, 120);
		score1.setBounds(width / 2 + 100, height / 2, 300, 120);
		time.setBounds(width / 2 - 50, height - height / 3, 300, 120);

		getContentPane().add(firstClown);
		getContentPane().add(secondClown);
		getContentPane().add(score1);
		getContentPane().add(score2);
		getContentPane().add(time);
		getContentPane().add(drawPanel);
		getContentPane().add(bgLabel);

		// final PlayerPublisher playerObserver1 = Publisher.getInstance()
		// .getPlayerObserver1();
		// final PlayerPublisher playerObserver2 = Publisher.getInstance()
		// .getPlayerObserver2();
		Publishers.getInstance().getPlayerPublisher1()
				.addObserver(new Observer() {

					@Override
					public void update(Observable o, Object arg) {
						// if (o == playerObserver1) {
						firstClown.setBounds(
								((PlayerPublisher) o).getPosition(), height
										- clownheight-52, clownWidth, clownheight);
						firstClown.setForeground(new Color(
								(int) (Math.random() * 255), (int) (Math
										.random() * 255),
								(int) (Math.random() * 255)));
					}
				});
		Publishers.getInstance().getPlayerPublisher2()
				.addObserver(new Observer() {

					@Override
					public void update(Observable o, Object arg) {
						// if (o == playerObserver2) {
						secondClown.setBounds(
								((PlayerPublisher) o).getPosition(), height
										- clownheight-52, clownWidth, clownheight);
						secondClown.setForeground(new Color((int) (Math
								.random() * 255), (int) (Math.random() * 255),
								(int) (Math.random() * 255)));
						// }
					}
				});

		Publishers.getInstance().getScorePublisher1()
				.addObserver(new Observer() {

					@Override
					public void update(Observable o, Object arg) {
						// System.out.println("fkdjkfnkdsjfkdjfkdjk");
						// if (o == playerObserver2) {
						// secondClown.setBounds((.getPosition(),
						// height - clownheight, clownWidth, clownheight);
						score1.setText("<html><center> Player 1<br> "
								+ ((ValuePublisher) o).getValue()
								+ " </center></html>");
						Sound.getInstance().startCheering();

					}
				});

		Publishers.getInstance().getScorePublisher2()
				.addObserver(new Observer() {

					@Override
					public void update(Observable o, Object arg) {
						// if (o == playerObserver2) {
						score2.setText("<html><center> Player 2<br> "
								+ ((ValuePublisher) o).getValue()
								+ " </center></html>");
						Sound.getInstance().startCheering();

						// }
					}
				});

		Publishers.getInstance().getTimePublisher().addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				// if (o == playerObserver2) {
				time.setText("<html><center> Time <br> "
						+ ((ValuePublisher) o).getValue() + " </center></html>");

				// }
			}
		});

	}

	public void reColor() {

		int r1 = (int) (Math.random() * 255), r2 = (int) (Math.random() * 255), r3 = (int) (Math
				.random() * 255);
//		while (r1 < 125) {
//			r1 = (int) (Math.random() * 255);
//		}
		score1.setForeground(new Color(r1, r2, r3));
		score2.setForeground(new Color(r2, r1, r3));
	}

	// public void positionFirstClown(int x) {
	// firstClown.setBounds(x, height - clownheight, clownWidth, clownheight);
	// }

	// public void positionSecondClown(int x) {
	// secondClown.setBounds(x, height - clownheight, clownWidth, clownheight);
	// }

	// public void updateFirstClownScore(String score) {
	// score1.setText("Player 1 \n" + score);
	// }

	// public void updateSecondClownScore(String score) {
	// score2.setText("Player 2 \n" + score);
	// }
}
