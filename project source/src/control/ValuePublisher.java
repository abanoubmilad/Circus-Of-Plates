/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.util.Observable;

// publisher
public class ValuePublisher extends Observable {

	private int value;

	// public ValuePublisher(int value) {
	// this.value = value;
	// }
	public void incValue(int extra) {
		this.value += extra;
		setChanged();
		notifyObservers();
	}
//
//	public void decValue(int value) {
//		this.value -= value;
//		setChanged();
//		notifyObservers();
//	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value=value;
		setChanged();
		notifyObservers();
	}

}
