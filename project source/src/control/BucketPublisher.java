/*
 created on : 27/12/2014
 modified on: 31/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.util.Observable;
import java.util.Observer;

import model.CircusInfo;

// publisher
public class BucketPublisher extends Observable {

	private int rightxBucket, leftxBucket, rightyBucket, leftyBucket;
	private CircusInfo circusInfo = CircusInfo.getInstance();
	private final int rightShift = circusInfo.getClownWidth() - 50;
//	private final int verticalShift = 30;
	private Observable playerObservable;

	// BucketPublisher(Observable playerObservable) {
	//
	// initialize();
	// }

	// public void setRightxBucket(int rightxBucket) {
	// this.rightxBucket = rightxBucket;
	// }

	// public void setRightyBucket(int rightyBucket) {
	// this.rightyBucket = rightyBucket;
	// }

	public void setPlayerObservable(Observable playerObservable) {
		this.playerObservable = playerObservable;
		leftxBucket = ((PlayerPublisher) playerObservable).getPosition();
		rightxBucket = leftxBucket + rightShift;
		
		leftxBucket-=10;
		
		rightyBucket = circusInfo.getCircusHeight()
				- circusInfo.getClownheight();
		leftyBucket = rightyBucket;
		initialize();

	}

	public void initialize() {
		playerObservable.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				leftxBucket = ((PlayerPublisher) o).getPosition();
				rightxBucket = leftxBucket + rightShift;
				leftxBucket-=10;

				setChanged();
				notifyObservers();
			}
		});
	}

	public int getRightxBucket() {
		return rightxBucket;
	}

	public int getLeftxBucket() {
		return leftxBucket;
	}

	public int getRightyBucket() {
		return rightyBucket;
	}

	public int getLeftyBucket() {
		return leftyBucket;
	}

//	public void incLeftyBucket() {
//		this.leftyBucket += verticalShift;
////		setChanged();
////		notifyObservers();
//	}
//
//	public void decLeftyBucket() {
//		this.leftyBucket -= verticalShift;
////		setChanged();
////		notifyObservers();
//	}
//
//	public void decRightyBucket() {
//		this.rightyBucket -= verticalShift;
////		setChanged();
////		notifyObservers();
//	}
//
//	public void incRightyBucket() {
//		this.rightyBucket += verticalShift;
////		setChanged();
////		notifyObservers();
//	}

}
