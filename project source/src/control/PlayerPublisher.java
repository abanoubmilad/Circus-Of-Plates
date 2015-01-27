/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.util.Observable;

// publisher
public class PlayerPublisher extends Observable {

	private int xPositition;

	public void setPosition(int xPositition) {
		this.xPositition = xPositition;
		setChanged();
		notifyObservers();
	}

	
	public int getPosition() {
		return this.xPositition;
	}

}
