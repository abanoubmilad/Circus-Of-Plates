/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package model;



public class Player {
	private int xPosition;
	private int yPosition;
//	private int score;
	private int rightBucketPosition;
	private int leftBucketPosition;

	public Player(int xPosition, int yPosition, int score,
			int rightBucketPosition, int leftBucketPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
//		this.score = score;
		this.rightBucketPosition = rightBucketPosition;
		this.leftBucketPosition = leftBucketPosition;
//		this.imgPath = imgPath;
	}

	public int getxPosition() {
		return xPosition;
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

//	public int getScore() {
//		return score;
//	}
//
//	public void setScore(int score) {
//		this.score = score;
//	}

	public int getRightBucketPosition() {
		return rightBucketPosition;
	}

	public void setRightBucketPosition(int rightBucketPosition) {
		this.rightBucketPosition = rightBucketPosition;
	}

	public int getLeftBucketPosition() {
		return leftBucketPosition;
	}

	public void setLeftBucketPosition(int leftBucketPosition) {
		this.leftBucketPosition = leftBucketPosition;
	}

//	public String getImgPath() {
//		return imgPath;
//	}
//
//	public void setImgPath(String imgPath) {
//		this.imgPath = imgPath;
//	}
//
//	public ImageIcon getImageIcon() {
//		if (imgPath != null) {
//			return new ImageIcon(CircusView.class.getResource(imgPath));
//		}
//		return null;
//	}
}
