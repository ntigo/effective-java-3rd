package com.coopnc.effectivejava3rd.item09.exam04;

import com.coopnc.effectivejava3rd.item09.exam02.FileDataWriter;
import com.coopnc.effectivejava3rd.item09.exam03.AutoCloseFileDataWriter;

/**
 * item 9 exam04
 *  - etc....
 */
public class ExamRun0904 {
	/**
	 * AutoCloseable 상속 받지 않은 경우에는 try () 에서 선언할 수 없음
	 */
	public void noAutoCloseableImplement() {
//        try ( FileDataWriter fileDataWriter = new FileDataWriter() ) {
//        } catch ( Exception e ) {
//        }
	}

	/**
	 * final instance 임으로 null 할당 및 새로운 인스턴스 할당이 불가하다.
	 */
	public void defaultFinalInstance() {
		try ( AutoCloseFileDataWriter autoCloseFileDataWriter = new AutoCloseFileDataWriter() ) {
			//autoCloseFile = null;
			//autoCloseFile = new AutoCloseFile();
        } catch ( Exception e ) {
        }
	}

	/**
	 * 코드가 지저분해진다
	 */
	public void dirtyCode() {
		FileDataWriter fileDataWriter1 = new FileDataWriter();
		FileDataWriter fileDataWriter2 = new FileDataWriter();

		try {
			// content...
		} catch( Exception e ) {
			// exception...
		} finally {
			try {
				fileDataWriter1.close();
			} catch ( Exception e ) {
				// close exception...
			}
		}

		try {
			// content...
		} catch( Exception e ) {
			// exception...
		} finally {
			try {
				fileDataWriter2.close();
			} catch ( Exception e ) {
				// close exception...
			}
		}
	}

	/**
	 * dirtyCode method와 비교
	 */
	public void cleanCode() {
		try ( AutoCloseFileDataWriter autoCloseFileDataWriter = new AutoCloseFileDataWriter() ) {
			// content...
		} catch( Exception e ) {
			// exception...
		}

		try ( AutoCloseFileDataWriter autoCloseFileDataWriter = new AutoCloseFileDataWriter() ) {
			// content...
		} catch( Exception e ) {
			// exception...
		}
	}
}

