/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.util.Observable;

import model.Plate;

// publisher
public class PlatesPublisher extends Observable {

	private Plate platesArr[];

	// private int value;
	//
	// // public ValuePublisher(int value) {
	// // this.value = value;
	// // }
	// public void incValue(int extra) {
	// this.value += extra;
	// setChanged();
	// notifyObservers();
	// }
	//
	// public void decValue(int value) {
	// this.value -= value;
	// setChanged();
	// notifyObservers();
	// }
	//
	public Plate[] getPlatesArr() {
		return platesArr;
	}

	//
	public void setToDrawArr(Plate [] platesArr) {
		this.platesArr = platesArr;
		setChanged();
		notifyObservers();
	}

}
