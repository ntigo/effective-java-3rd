package com.coopnc.effectivejava3rd.item09.exam05;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class AutoCloseSocketClient implements AutoCloseable {
	//소켓 생성
	Socket socket = new Socket();

	public void start() throws Exception {
		SocketAddress address = new InetSocketAddress( "127.0.0.1", 52525 );
		socket.connect( address );

		System.out.println( "socket connect : " + socket.getLocalAddress().toString() + " / " + socket.getLocalPort() );
	}

	public void close() {
		System.out.println( "client socket close..." );

		try {
			if ( socket != null && socket.isConnected() ) {
				socket.close();
			}
		} catch( IOException e ) {
			System.out.println( "===== AutoCloseSocketClient close method error =====" );
			System.out.println( e );
		}
	}
}
