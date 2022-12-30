package com.coopnc.effectivejava3rd.item09.exam05;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	ServerSocket serverSocket;
	ExecutorService executorService = Executors.newFixedThreadPool( 5 );

	public SocketServer() {
	}

	public void accept() {
		try {
			serverSocket = new ServerSocket( 52525 );

			while ( !serverSocket.isClosed() ) {
				System.out.println( "connect stay..." );
				Socket acceptSocket = serverSocket.accept();

				Runnable runnable = () -> {
					start( acceptSocket );
				};
				executorService.execute( runnable );
			}
		} catch( Exception e ) {
			System.out.println( "===== accept method error =====" );
			System.out.println( e.toString() );
		}
	}

	public void start( Socket socket ) {
		try {
			socket.setSoLinger( true, 0 );
			System.out.println( socket.getLocalAddress().toString() + " / " + socket.getLocalPort() + " server socket close" );
			socket.close();
		} catch( Exception e ) {
			System.out.println( e.toString() );
		}
	}

	public void close() {
		System.out.println( "===== server socket close =====" );

		try {
			if ( serverSocket != null && !serverSocket.isClosed() ) {
				serverSocket.close();
			}
		} catch( Exception e ) {
			System.out.println( e.toString() );
		}
	}
}
