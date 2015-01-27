/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */

package model;

public class CircusInfo {
	private static CircusInfo info = new CircusInfo();
	private int CircusWidth, CircusHeight;
	private final int clownWidth = 140, clownheight = 180;

	public int getClownWidth() {
		return clownWidth;
	}

	public int getClownheight() {
		return clownheight;
	}

	private CircusInfo() {
	}

	public static CircusInfo getInstance() {
		return info;
	}

	public int getCircusWidth() {
		return CircusWidth;
	}

	public void setCircusWidth(int circusWidth) {
		CircusWidth = circusWidth;
	}

	public int getCircusHeight() {
		return CircusHeight;
	}

	public void setCircusHeight(int circusHeight) {
		CircusHeight = circusHeight;
	}

}
