/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageScaler {
private static ImageScaler imgScaler = new ImageScaler();
	private ImageScaler() {
	}
	public static ImageScaler getInstance(){
		return imgScaler;
	}
	public ImageIcon scaleImage(int WIDTH, int HEIGHT, String filename) {
	    BufferedImage bi = null;
	    try {
	        ImageIcon ii = new ImageIcon(CircusView.class.getResource(filename));//path to image
	        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = (Graphics2D) bi.createGraphics();
	        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	        g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return new ImageIcon((Image) bi);
	}

}
