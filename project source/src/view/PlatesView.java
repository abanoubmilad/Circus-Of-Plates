/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.CircusInfo;
import model.Plate;
import control.PlatesPublisher;
import control.PlayerPublisher;
import control.Publishers;

public class PlatesView extends JPanel {

	private static final long serialVersionUID = 5507901340439608053L;
	private static PlatesView drawpanel = new PlatesView();
	private Plate[] toDraw;

	public static PlatesView getInstance() {
		return drawpanel;
	}

	private PlatesView() {
		Publishers.getInstance().getPlatesPublisher()
				.addObserver(new Observer() {

					@Override
					public void update(Observable o, Object arg) {
						toDraw = ((PlatesPublisher) o).getPlatesArr();
						repaint();
					}
				});
		// initialize();
	}

	//
	// private void initialize() {
	// drawPanel.setBounds(0, 0, width, height);
	// drawPanel.setOpaque(false);
	// }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ArrayList<Plate> list = FallingPlates.getInstance().getList();
		Plate p;
		// System.out.println(toDraw.length);
		System.out.println();
//		System.out.println();
		for (int i = 0; i < toDraw.length; i++) {
			p = toDraw[i];
			p.draw(g);

		}

		PlayerPublisher pb1 = Publishers.getInstance().getPlayerPublisher1();
		PlayerPublisher pb2 = Publishers.getInstance().getPlayerPublisher2();

		int r1 = (int) (Math.random() * 255), r2 = (int) (Math.random() * 255), r3 = (int) (Math
				.random() * 255);
		while (r1 < 125) {
			r1 = (int) (Math.random() * 255);
		}

		g.setColor(new Color(r1, r2, r3));
		int y = CircusInfo.getInstance().getCircusHeight()
				- CircusInfo.getInstance().getClownheight();
		g.fillRect(pb1.getPosition(), y, 45, 40);
		g.setColor(new Color(r2, r3, r1));
		g.fillRect(pb1.getPosition() + CircusInfo.getInstance().getClownWidth()
				- 45, y, 45, 40);
		g.setColor(new Color(r3, r2, r1));
		g.fillRect(pb2.getPosition(), y, 45, 40);
		g.setColor(new Color(r3, r1, r2));
		g.fillRect(pb2.getPosition() + CircusInfo.getInstance().getClownWidth()
				- 45, y, 45, 40);
		
		CircusView.getInstance().reColor();
	}
	// public void startDrawing(){
	// repaint();
	// }

}
