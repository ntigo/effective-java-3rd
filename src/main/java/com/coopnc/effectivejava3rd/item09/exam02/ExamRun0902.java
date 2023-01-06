package com.coopnc.effectivejava3rd.item09.exam02;

/**
 * item 9 exam02
 *  - try-finally 예제
 */
public class ExamRun0902 {
	/**
	 * 정상 케이스
	 */
	public static void start() {
		FileDataWriter fileDataWriter = new FileDataWriter();

		try {
			String fileName = fileDataWriter.getTestFilePath();
			System.out.println( "===== file name =====" );
			System.out.println( fileName );

			fileDataWriter.fileOpen( fileName );
			fileDataWriter.fileWrite( "effective java 3rd" );
		} catch ( Exception e ) {
			System.out.println( "===== exam run 0902 start method error =====" );
			System.out.println( e.toString() );
		} finally {
			try {
				fileDataWriter.close();
			} catch( Exception e ) {
				System.out.println( e.toString() );
			}
		}
	}

	/**
	 * 문제가 있는 케이스
	 */
	public static void wrong() {
		FileDataWriter fileDataWriter = new FileDataWriter();

		try {
			String fileName = fileDataWriter.getTestFilePath();
			System.out.println( "===== file name =====" );
			System.out.println( fileName );

			fileDataWriter.fileOpen( fileName );
			fileDataWriter.fileWrite( "effective java 3rd" );
		} catch ( Exception e ) {
			System.out.println( "===== exam run 0902 wrong method error =====" );
			System.out.println( e.toString() );
		} finally {
			// 개발자가 실수로 flush, close등을 처리하지 않아 파일에 반영되지 않음
		}
	}
}

