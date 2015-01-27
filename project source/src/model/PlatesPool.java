package model;

public class PlatesPool {

	private Plate[] pool = new Plate[100];
	private boolean[] inPool = new boolean[100];
	public static PlatesPool platesPool = new PlatesPool();
	private PlateFactory plateFactory = PlateFactory.getInstance();
	private int width = CircusInfo.getInstance().getCircusWidth();

	public static PlatesPool getInstance() {
		return platesPool;
	}

	private PlatesPool() {
		for (int i = 0; i < 100; i++) {
			pool[i] = plateFactory.generatePlate();
			inPool[i] = true;
		}
	}

	public int pull() {
		for (int j = 0; j < inPool.length; j++) {
			if (inPool[j]) {
				inPool[j] = false;
				return j;
			}
		}
		return -1;
	}

	public void push(int j) {
		inPool[j] = true;
		pool[j].setxPosition((int) (Math.random() * width));
		pool[j].setyPosition((int) (Math.random() * -1500));
	}

	public Plate getPlate(int j) {
		return pool[j];
	}

	public Plate[] getPlateList(boolean[] mask) {
		int size = 0;
		for (int i = 0; i < mask.length; i++) {
			if (mask[i]) {
				size++;
			}
		}
		Plate[] toDraw = new Plate[size];

		for (int i = 0, e = 0; i < mask.length; i++) {
			if (mask[i]) {
				toDraw[e++] = pool[i];
			}
		}
		return toDraw;
	}
}
