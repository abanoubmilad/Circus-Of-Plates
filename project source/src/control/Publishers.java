/*
 created on : 30/12/2014
 modified on: 31/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */

package control;

public class Publishers {
	private static Publishers publisher = new Publishers();
	private PlayerPublisher playerPublisher1, playerPublisher2;
	private ValuePublisher scorePublisher1, scorePublisher2, timePublisher;
	private BucketPublisher bucketPublisher1, bucketPublisher2;
	private PlatesPublisher platesPublisher;

	private Publishers() {
		playerPublisher1 = new PlayerPublisher();
		playerPublisher2 = new PlayerPublisher();
		scorePublisher1 = new ValuePublisher();
		scorePublisher2 = new ValuePublisher();
		timePublisher = new ValuePublisher();
		platesPublisher = new PlatesPublisher();
		bucketPublisher1 = new BucketPublisher();
		bucketPublisher2 = new BucketPublisher();

	}

	public static Publishers getInstance() {
		return publisher;
	}

	//
	// public static Publisher getPublisher() {
	// return publisher;
	// }

	public PlayerPublisher getPlayerPublisher1() {
		return playerPublisher1;
	}

	public BucketPublisher getBucketPublisher1() {
		return bucketPublisher1;
	}

	public BucketPublisher getBucketPublisher2() {
		return bucketPublisher2;
	}

	public PlayerPublisher getPlayerPublisher2() {
		return playerPublisher2;
	}

	public ValuePublisher getScorePublisher1() {
		return scorePublisher1;
	}

	public ValuePublisher getScorePublisher2() {
		return scorePublisher2;
	}

	public ValuePublisher getTimePublisher() {
		return timePublisher;
	}

	public PlatesPublisher getPlatesPublisher() {
		return platesPublisher;
	}

}
