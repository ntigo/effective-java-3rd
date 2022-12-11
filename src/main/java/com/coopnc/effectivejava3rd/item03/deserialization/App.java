package com.coopnc.effectivejava3rd.item03.deserialization;

import java.io.*;

public class App {

    public static void main() throws IOException, ClassNotFoundException {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = null;

        try(ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.obj"))){
            out.writeObject(singleton);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.obj"))){
            singleton2 = (Singleton) in.readObject();
        }

        System.out.printf("deserialization 결과: %s", singleton == singleton2);

    }
}
