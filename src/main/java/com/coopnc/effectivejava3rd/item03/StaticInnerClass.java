public class StaticInnerClass {
	private StaticInnerClass(){

	}
	private static class StaticInnerClassHolder{
		private static final StaticInnerClass instance = new StaticInnerClass();
	}

	public  static StaticInnerClass getInstance(){
		return StaticInnerClassHolder.instance;
	}

	public static void doTest() {

	}

}