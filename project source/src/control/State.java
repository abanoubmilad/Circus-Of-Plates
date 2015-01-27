package control;

public class State {
	public static final int FALLING = 1;
	public static final int CONTACT = 2;
	public int CONTACTINDEX = 0;

	public int getCONTACTINDEX() {
		return CONTACTINDEX;
	}

	public void setCONTACTINDEX(int cONTACTINDEX) {
		CONTACTINDEX = cONTACTINDEX;
	}

	public static final int FLOOR = 3;
	public static final int APPEAR = 4;
	private int currentState;

	public State() {
		currentState = APPEAR;
	}

	public int getState() {
		return currentState;
	}

	public void setState(int currentState) {
		this.currentState = currentState;
	}
	//
	// public void update(Plate plate) {
	//
	// }

}
