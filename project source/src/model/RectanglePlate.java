package model;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import view.CircusView;

import control.State;

public class RectanglePlate extends Plate {

	// Image img;
	//
	// public SquarePlate(int xPosition, int yPosition, int color, State state)
	// {
	// super(xPosition, yPosition, color, state);
	// super.img = null;
	// }

	public RectanglePlate(int xPosition, int yPosition, int color, State state) {
		super(xPosition, yPosition, color, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// 0 blue
		// 1 red
		// 2 green
		switch (this.getColor()) {
		case 0:
			ImageIcon img0 = new ImageIcon(
					CircusView.class.getResource("/pics/rb.png"));
			g.drawImage(img0.getImage(), getxPosition(),
					getyPosition(), null);
			break;
		case 1:
			ImageIcon img1 = new ImageIcon(
					CircusView.class.getResource("/pics/rr.png"));
			g.drawImage(img1.getImage(), getxPosition(),
					getyPosition(), null);
			break;
		default:
			ImageIcon img2 = new ImageIcon(
					CircusView.class.getResource("/pics/rp.png"));
			g.drawImage(img2.getImage(),getxPosition(),
					getyPosition(), null);
			break;
		}

		// TODO Auto-generated method stub
		// new javafx.scene.image.Image(new File("/pics/fc.gif"));

		// Image img= jgetImage();
		// (Image)(new
		// ImageIcon(SquarePlate.class.getResource("/pics/fc.gif")));
		// g.drawImage(img, super.getxPosition(), super.getyPosition(), null);
	}
	// draw
	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// }

}
