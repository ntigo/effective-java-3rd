package com.coopnc.effectivejava3rd.item10.exam03;

import java.net.InetAddress;
import java.net.URL;

public class ConsistencyTest {
    public static void main( String[] args) throws Exception {
        URL url1 = new URL("http://google.com");
        URL url2 = new URL("http://google.com");

        // 일관성 깨질 위험
        System.out.println(url1.equals(url2) );
//        Thread.sleep(5000);
        System.out.println(url2.equals(url1) );

        InetAddress a1 = InetAddress.getByName(url1.getHost());
        InetAddress a2 = InetAddress.getByName(url2.getHost());
        System.out.println(a1.getHostAddress());
        System.out.println(a2.getHostAddress());
    }
}
