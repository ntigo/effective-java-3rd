package com.coopnc.effectivejava3rd.item09.exam03;

import java.nio.file.Paths;

/**
 * item 9 exam03
 *  - try-with-resources 예제
 */
public class ExamRun0903 {
	public static void start() {
		try ( AutoCloseFileDataWriter autoCloseFileDataWriter = new AutoCloseFileDataWriter() ) {
			String fileName = autoCloseFileDataWriter.getTestFilePath();
			System.out.println( "===== file name =====" );
			System.out.println( fileName );

			autoCloseFileDataWriter.fileOpen( fileName );
			autoCloseFileDataWriter.fileWrite( "effective java 3rd" );
		} catch ( Exception e ) {
			System.out.println( "===== exam run 0903 start method error =====" );
			System.out.println( e.toString() );
		}
	}
}

