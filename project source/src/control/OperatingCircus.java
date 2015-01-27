/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.CircusInfo;
import model.Plate;
import model.PlatesPool;
import view.Winner;

public class OperatingCircus implements Runnable {
	private CircusInfo circusInfo = CircusInfo.getInstance();
	// private int width = circusInfo.getCircusWidth();
	private int height = circusInfo.getCircusHeight();

	private boolean[] dropdPlates = new boolean[100];
	private boolean[] toDraw = new boolean[100];

	private boolean[] rBucketP1 = new boolean[100];
	private boolean[] rBucketP2 = new boolean[100];
	private boolean[] lBucketP1 = new boolean[100];
	private boolean[] lBucketP2 = new boolean[100];

	private ArrayList<Integer> rBucketP1Holder = new ArrayList<Integer>();
	private ArrayList<Integer> rBucketP2Holder = new ArrayList<Integer>();
	private ArrayList<Integer> lBucketP1Holder = new ArrayList<Integer>();
	private ArrayList<Integer> lBucketP2Holder = new ArrayList<Integer>();

	// private int rbp1Count = 0, rbp2Count = 0, lbp1Count = 0, lbp2Count = 0;

	private int p1rx, p1lx, p1ry, p1ly;
	private int p2rx, p2lx, p2ry, p2ly;

	private static OperatingCircus operatingCircus = new OperatingCircus();
	private Publishers publisher = Publishers.getInstance();
	private Controller controller = Controller.getInstance();
	private PlatesPool platesPool = PlatesPool.getInstance();
	private PlatesPublisher platesPublisher = publisher.getPlatesPublisher();
	private ValuePublisher scorePublisher1 = publisher.getScorePublisher1(),
			scorePublisher2 = publisher.getScorePublisher2();
	private BucketPublisher bucketPublisher1 = publisher.getBucketPublisher1(),
			bucketPublisher2 = publisher.getBucketPublisher2();

	public static OperatingCircus getInstance() {
		return operatingCircus;
	}

	private OperatingCircus() {
		p1ry = circusInfo.getCircusHeight() - circusInfo.getClownheight();
		p1ly = p1ry;
		p2ry = p1ry;
		p2ly = p1ry;

		bucketPublisher1.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				BucketPublisher pb = (BucketPublisher) o;
				p1rx = pb.getRightxBucket();
				p1lx = pb.getLeftxBucket();

				Plate plate;
				for (int i = 0; i < rBucketP1.length; i++) {
					if (rBucketP1[i]) {
						plate = platesPool.getPlate(i);
						plate.setxPosition(p1rx);
					}
				}
				for (int i = 0; i < lBucketP1.length; i++) {
					if (lBucketP1[i]) {
						plate = platesPool.getPlate(i);
						plate.setxPosition(p1lx);
					}
				}
			}
		});

		bucketPublisher2.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				BucketPublisher pb = (BucketPublisher) o;
				p2rx = pb.getRightxBucket();
				p2lx = pb.getLeftxBucket();
				// p2ry = pb.getRightyBucket();
				// p2ly = pb.getLeftyBucket();
				Plate plate;
				for (int i = 0; i < rBucketP2.length; i++) {
					if (rBucketP2[i]) {
						plate = platesPool.getPlate(i);
						plate.setxPosition(p2rx);
					}
				}
				for (int i = 0; i < lBucketP2.length; i++) {
					if (lBucketP2[i]) {
						plate = platesPool.getPlate(i);
						plate.setxPosition(p2lx);
					}
				}
			}
		});
	}

	@Override
	public void run() {
		// Thread thread = new Thread(new GeneratingPlates());
		// thread.start();
		scorePublisher1.setValue(0);
		scorePublisher2.setValue(0);

		int counter = 0;
		while (!controller.isTimeOut()) {
			try {
				Thread.sleep(14);
				counter++;
				dropPlate();
				updatePlates();
				// 1000/14
				if (counter == 71) {
					counter = 0;
					controller.updateTime();
				}
			} catch (InterruptedException e) {
				System.out.println("thread problem occured!");
			}

		}
		int score1 = scorePublisher1.getValue();
		int score2 = scorePublisher2.getValue();
		Winner winner = Winner.getInstance();
		if (score1 == score2) {
			winner.set("Tie");
		} else if (score1 > score2) {
			winner.set("Player1");
		} else {
			winner.set("Player2");

		}

	}

	private void dropPlate() {
		if ((int) (Math.random() * 2) != 1) {
			int p = platesPool.pull();
			if (p != -1) {
				dropdPlates[p] = true;
			}
		}
	}

	private void updatePlates() {
		Plate plate;

		for (int i = 0; i < toDraw.length; i++) {
			toDraw[i] = false;
		}

		for (int i = 0; i < dropdPlates.length; i++) {
			if (dropdPlates[i]) {
				plate = platesPool.getPlate(i);

				if (plate.getyPosition() > height) {
					dropdPlates[i] = false;
					platesPool.push(i);
				} else {
					int x = plate.getxPosition(), y = plate.getyPosition();

					if (y < p1ry && Math.abs(y - p1ry) == 30
							&& Math.abs(x - p1rx) < 15) {
						dropdPlates[i] = false;
						toDraw[i] = true;
						rBucketP1[i] = true;
						scorePublisher1.incValue(100);
						p1ry -= 30;
						// bucketPublisher1.decRightyBucket();
						rBucketP1Holder.add(i);
						// plate.getState().setCONTACTINDEX(rbp1Count);
						if (rBucketP1Holder.size() >= 3) {
							int index = rBucketP1Holder.size() - 1;
							int p1 = rBucketP1Holder.get(index - 1);
							int p2 = rBucketP1Holder.get(index - 2);
							Plate temp1 = platesPool.getPlate(p1);
							Plate temp2 = platesPool.getPlate(p2);

							if (plate.getColor() == temp1.getColor()
									&& plate.getColor() == temp2.getColor()) {

								scorePublisher1.incValue(500);

								rBucketP1Holder.remove(index);
								rBucketP1Holder.remove(index - 1);
								rBucketP1Holder.remove(index - 2);

								platesPool.push(p1);
								rBucketP1[p1] = false;
								platesPool.push(p2);
								rBucketP1[p2] = false;
								platesPool.push(i);
								rBucketP1[i] = false;
								toDraw[i] = false;
								toDraw[p1] = false;
								toDraw[p2] = false;
								//
								dropdPlates[i] = false;
								dropdPlates[p1] = false;
								dropdPlates[p2] = false;
								//
								p1ry += 90;

								// bucketPublisher1.incRightyBucket();
								// bucketPublisher1.incRightyBucket();
								// bucketPublisher1.incRightyBucket();
								// bucketPublisher1.incRightyBucket();
								// bucketPublisher1.incRightyBucket();
								// bucketPublisher1.incRightyBucket();

							}

						}
					} else if (y < p1ly && Math.abs(y - p1ly) == 30
							&& Math.abs(x - p1lx) < 15) {
						dropdPlates[i] = false;
						toDraw[i] = true;
						lBucketP1[i] = true;
						scorePublisher1.incValue(100);
						// bucketPublisher1.decLeftyBucket();
						p1ly -= 30;

						lBucketP1Holder.add(i);
						if (lBucketP1Holder.size() >= 3) {
							int index = lBucketP1Holder.size() - 1;
							int p1 = lBucketP1Holder.get(index - 1);
							int p2 = lBucketP1Holder.get(index - 2);
							Plate temp1 = platesPool.getPlate(p1);
							Plate temp2 = platesPool.getPlate(p2);

							if (plate.getColor() == temp1.getColor()
									&& plate.getColor() == temp2.getColor()) {

								scorePublisher1.incValue(500);
								lBucketP1Holder.remove(index);
								lBucketP1Holder.remove(index - 1);
								lBucketP1Holder.remove(index - 2);

								platesPool.push(p1);
								lBucketP1[p1] = false;
								platesPool.push(p2);
								lBucketP1[p2] = false;
								platesPool.push(i);
								lBucketP1[i] = false;
								toDraw[i] = false;
								toDraw[p1] = false;
								toDraw[p2] = false;
								//
								p1ly += 90;

								dropdPlates[i] = false;
								dropdPlates[p1] = false;
								dropdPlates[p2] = false;
								//
								// bucketPublisher1.incLeftyBucket();
								// bucketPublisher1.incLeftyBucket();
								// bucketPublisher1.incLeftyBucket();
								// bucketPublisher1.incLeftyBucket();
								// bucketPublisher1.incLeftyBucket();
								// bucketPublisher1.incLeftyBucket();

							}

						}
					} else if (y < p2ry && Math.abs(y - p2ry) == 30
							&& Math.abs(x - p2rx) < 15) {
						dropdPlates[i] = false;
						toDraw[i] = true;
						rBucketP2[i] = true;
						scorePublisher2.incValue(100);
//						bucketPublisher2.decRightyBucket();
						p2ry -= 30;

						rBucketP2Holder.add(i);
						if (rBucketP2Holder.size() >= 3) {
							int index = rBucketP2Holder.size() - 1;
							int p1 = rBucketP2Holder.get(index - 1);
							int p2 = rBucketP2Holder.get(index - 2);
							Plate temp1 = platesPool.getPlate(p1);
							Plate temp2 = platesPool.getPlate(p2);

							if (plate.getColor() == temp1.getColor()
									&& plate.getColor() == temp2.getColor()) {

								scorePublisher2.incValue(500);
								rBucketP2Holder.remove(index);
								rBucketP2Holder.remove(index - 1);
								rBucketP2Holder.remove(index - 2);

								platesPool.push(p1);
								rBucketP2[p1] = false;
								platesPool.push(p2);
								rBucketP2[p2] = false;
								platesPool.push(i);
								rBucketP2[i] = false;
								toDraw[i] = false;
								toDraw[p1] = false;
								toDraw[p2] = false;
								p2ry += 90;

								//
								dropdPlates[i] = false;
								dropdPlates[p1] = false;
								dropdPlates[p2] = false;
								//
								// bucketPublisher2.incRightyBucket();
								// bucketPublisher2.incRightyBucket();
								// bucketPublisher2.incRightyBucket();

								// bucketPublisher2.incRightyBucket();
								// bucketPublisher2.incRightyBucket();
								// bucketPublisher2.incRightyBucket();

							}

						}

					} else if (y < p2ly && Math.abs(y - p2ly) == 30
							&& Math.abs(x - p2lx) < 15) {
						dropdPlates[i] = false;
						toDraw[i] = true;
						lBucketP2[i] = true;
						scorePublisher2.incValue(100);
//						bucketPublisher2.decLeftyBucket();
						p2ly -= 30;

						lBucketP2Holder.add(i);
						if (lBucketP2Holder.size() >= 3) {
							int index = lBucketP2Holder.size() - 1;
							int p1 = lBucketP2Holder.get(index - 1);
							int p2 = lBucketP2Holder.get(index - 2);
							Plate temp1 = platesPool.getPlate(p1);
							Plate temp2 = platesPool.getPlate(p2);

							if (plate.getColor() == temp1.getColor()
									&& plate.getColor() == temp2.getColor()) {

								scorePublisher2.incValue(500);
								lBucketP2Holder.remove(index);
								lBucketP2Holder.remove(index - 1);
								lBucketP2Holder.remove(index - 2);

								platesPool.push(p1);
								platesPool.push(i);
								platesPool.push(p2);

								lBucketP2[p1] = false;
								lBucketP2[p2] = false;
								lBucketP2[i] = false;

								toDraw[i] = false;
								toDraw[p1] = false;
								toDraw[p2] = false;
								p2ly += 90;

								//
								dropdPlates[i] = false;
								dropdPlates[p1] = false;
								dropdPlates[p2] = false;
								//
								// bucketPublisher2.incLeftyBucket();
								// bucketPublisher2.incLeftyBucket();
								// bucketPublisher2.incLeftyBucket();
								// bucketPublisher2.incLeftyBucket();
								// bucketPublisher2.incLeftyBucket();
								// bucketPublisher2.incLeftyBucket();

							}

						}

					} else {
						plate.setyPosition(plate.getyPosition() + 1);
						toDraw[i] = true;
					}

				}

			}
		}

		for (int i = 0; i < rBucketP1.length; i++) {
			if (rBucketP1[i]) {
				toDraw[i] = true;
			} else if (rBucketP2[i]) {
				toDraw[i] = true;
			} else if (lBucketP1[i]) {
				toDraw[i] = true;
			} else if (lBucketP2[i]) {
				toDraw[i] = true;
			}
		}

		platesPublisher.setToDrawArr(platesPool.getPlateList(toDraw));

	}

}
