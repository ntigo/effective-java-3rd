package com.coopnc.effectivejava3rd.item03;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest{

	public static void doTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
		Factory singleton = Factory.getInstance();
		Factory singleton2 = null;
		try(ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.obj"))){
			out.writeObject(singleton);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try (ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.obj"))){
			singleton2 = (Factory) in.readObject();
		}

		System.out.printf("deserialization 결과: %s", singleton == singleton2);


		Constructor<Factory> constructor = null;
		constructor = Factory.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		singleton2 = constructor.newInstance();

		System.out.printf("리플렉션 결과: %s ", singleton == singleton2);

	}


}
