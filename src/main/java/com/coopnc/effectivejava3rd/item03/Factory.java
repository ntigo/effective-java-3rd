public class Factory {
	private static final Factory instance = new Factory();

	private Factory(){}

	public static Factory getInstance(){
		return instance
	}


	public static void doTest() {

	}

}