public class SingletionTest{

	public static void doTest() {
		Singleton singleton = Basic.getInstance();
		Singleton singleton2 = null;

		try(ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.obj"))){
			out.writeObject(singleton);
		}

		try (ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.obj"))){
			singleton2 = (Singleton) in.readObject();
		}

		System.out.printf("deserialization 결과: %s", singleton == singleton2);


		Constructor<Singleton> constructor = null;
		constructor = Singleton.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		Singleton singleton2 = null;
		singleton2 = constructor.newInstance();

	}


}