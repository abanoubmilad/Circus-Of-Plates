/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package model;


import java.awt.Graphics;

import control.State;

public abstract class  Plate {

	private int xPosition;
	private int yPosition;
	private int color;
//	private final int height=30,width=50;
	private State state;
	public int getxPosition() {
		return xPosition;
	}
	public Plate(int xPosition, int yPosition, int color, State state) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.color = color;
		this.state = state;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
//	public int getHeight() {
//		return height;
//	}
//	public int getWidth() {
//		return width;
//	}
	
	public void draw(Graphics g) {
	}
//	private boolean right;
//	private JLabel jlabel;
//	private Image img;
//	
//	
//	public int getxPosition() {
//		return xPosition;
//	}
//	public boolean right() {
//		return right;
//	}
//
//	public Plate(boolean right,int xPosition, int yPosition) {
//		this.right=right;
//		this.xPosition = xPosition;
//		this.yPosition = yPosition;
//		jlabel = new JLabel();
//		jlabel.setIcon(new ImageIcon(CircusView.class
//				.getResource("/pics/s2.png")));
//		jlabel.setBounds(xPosition,yPosition, 50,10);
//		this.img= Toolkit.getDefaultToolkit().getImage("/pics/s2.png");
//	}
//
//
//	public Image getImg() {
//		return img;
//	}
//	public void setImg(Image img) {
//		this.img = img;
//	}
//	public void setPosition(int xPosition,int yPosition) {
//		this.xPosition = xPosition;
//		this.yPosition = yPosition;
//		jlabel.setBounds(xPosition,yPosition, 50,10);
//
//	}
//
//
//	public int getyPosition() {
//		return yPosition;
//	}
//
//
//
//	public JLabel getJlabel() {
//		return jlabel;
//	}
//
//
//	public void setJlabel(JLabel jlabel) {
//		this.jlabel = jlabel;
//	}


}
