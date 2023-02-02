package com.coopnc.effectivejava3rd.item09.exam05;

/**
 * item 9 exam04
 *  - try-with-resources socket 예제
 */
public class ExamRun0905 {
	private static SocketServer socketServer = null;

	public static void serverStart() {
		if ( socketServer == null ) {
			socketServer = new SocketServer();

			Thread acceptThread = new Thread( () -> {
				socketServer.accept();
			} );
			acceptThread.start();
		}
	}

	public static void serverEnd() {
		if ( socketServer != null ) {
			socketServer.close();
		}
	}

	public static void connect() {
		try ( AutoCloseSocketClient autoCloseSocketClient = new AutoCloseSocketClient() ) {
			autoCloseSocketClient.start();
			Thread.sleep( 500 );
		} catch( Exception e ) {
			System.out.println( e );
		}
	}
}

