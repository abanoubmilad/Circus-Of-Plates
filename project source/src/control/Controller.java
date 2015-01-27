/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.util.Set;

import model.CircusInfo;

public class Controller {

	private static Controller controller = new Controller();

	private Controller() {
		initialize();
	}

	public static Controller getInstance() {
		return controller;
	}

	// private Player p1, p2;
	// private CircusView circus;
	private CircusInfo circusInfo;
	private int width,clownWidth;
	private final int shift = 25;
	private Publishers publisher = Publishers.getInstance();
	private int time;
	private PlayerPublisher playerPublisher1 = publisher.getPlayerPublisher1(),
			playerPublisher2 = publisher.getPlayerPublisher2();
	private ValuePublisher timPublisher = publisher.getTimePublisher();
	private BucketPublisher bucketPublisher1 = publisher.getBucketPublisher1(),
			bucketPublisher2 = publisher.getBucketPublisher2();

	// private int p1rbx, p1lbx, p2rbx, p2lbx;
	// private int p1rby, p1lby, p2rby, p2lby;

	private void initialize() {
		time = 60;
		// circus = CircusView.getInstance();
		circusInfo = CircusInfo.getInstance();

		width = circusInfo.getCircusWidth();
		clownWidth = circusInfo.getClownWidth();

		playerPublisher2.setPosition(width / 4);
		playerPublisher1.setPosition(width - width / 4 - clownWidth);

		timPublisher.setValue(60);

		bucketPublisher1.setPlayerObservable(playerPublisher1);
		bucketPublisher2.setPlayerObservable(playerPublisher2);


	}



	public void movePlayers(Set<Integer> list) {
		for (int key : list) {
			switch (key) {
			// left arrow
			case 37:
				if (playerPublisher1.getPosition() > 0) {
					playerPublisher1.setPosition(playerPublisher1.getPosition()
							- shift);
				}
				break;
			// right arrow
			case 39:
				if (playerPublisher1.getPosition() + clownWidth < width) {
					playerPublisher1.setPosition(playerPublisher1.getPosition()
							+ shift);
				}
				break;
			// A button
			case 65:
				if (playerPublisher2.getPosition() > 0) {
					playerPublisher2.setPosition(playerPublisher2.getPosition()
							- shift);

				}
				break;
			// D button
			case 68:
				if (playerPublisher2.getPosition() + clownWidth < width) {
					playerPublisher2.setPosition(playerPublisher2.getPosition()
							+ shift);

				}
				break;
			default:
				break;
			}
			// updateClownBuckets();
		}
	}


	public void updateTime() {
		timPublisher.setValue(--this.time);
	}

	public boolean isTimeOut() {
		return this.time <= 0;
	}


}
