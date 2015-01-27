/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package model;

import control.State;

public class PlateFactory {

	private int width = CircusInfo.getInstance().getCircusWidth();
//	private int height = CircusInfo.getInstance().getCircusHeight();
//	private int topShell = height / 5;
//	private int downShell = height / 20;

	private static PlateFactory plateFactory = new PlateFactory();

	private PlateFactory() {
	}

	public static PlateFactory getInstance() {
		return plateFactory;
	}

	// // int rand =
	//
	// // switch (c) {
	// // case 's':
	// //
	// // break;
	// //
	// // default:
	// // break;
	// // }
	// }

	public Plate generatePlate() {
//		int x, y;
//		if ((int) (Math.random() * 2) == 1) {
//			x = (int) (Math.random() * -50);
//		} else {
//			x = (int) (Math.random() * 50) + width;
//		}
//		if ((int) (Math.random() * 2) == 1) {
//			y = topShell;
//		} else {
//			y = downShell;
//		}
		int x = (int) (Math.random() * width),
		y = (int) (Math.random() * -1500);

		// 0 blue
		// 1 red
		// 2 green

		if ((int) (Math.random() * 2) == 1) {
			return new SquarePlate(x, y, (int) (Math.random() * 3), new State());
		} else {
			return new RectanglePlate(x, y, (int) (Math.random() * 3), new State());

		}

	}
}
