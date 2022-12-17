package com.ntigo.study.effectivejava3rd.item03.assist;

import java.io.*;

public class Serialization {
    public static void serialize( Object obj, String fileName ) {
        try ( ObjectOutput out = new ObjectOutputStream( new FileOutputStream( fileName ) ) ) {
            out.writeObject( obj );
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    public static Object deserialize( String fileName ) {
        try ( ObjectInput in = new ObjectInputStream( new FileInputStream( fileName ) ) ) {
            return in.readObject();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        } catch ( ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }
    }

    public static void doTest() {
        String fileName = "serial.obj";

        SerializationSingleton serialIns = SerializationSingleton.getInstance();
        serialize( serialIns, fileName );
        System.out.println( serialIns );
        serialIns.display();

        SerializationSingleton deserializeIns = (SerializationSingleton) deserialize( fileName );
        System.out.println( deserializeIns );
        deserializeIns.display();
    }
}
