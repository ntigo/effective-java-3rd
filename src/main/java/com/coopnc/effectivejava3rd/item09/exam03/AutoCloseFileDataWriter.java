package com.coopnc.effectivejava3rd.item09.exam03;

import com.coopnc.effectivejava3rd.item09.BaseFileWriter;

import java.io.IOException;

public class AutoCloseFileDataWriter extends BaseFileWriter implements AutoCloseable {
	public AutoCloseFileDataWriter() {
	}

	@Override
	public void close() {
		try {
			if ( fileWriter != null ) {
				fileWriter.close();
				System.out.println( "file close end" );
			}
		} catch ( IOException e ) {
			System.out.println( e );
		}
	}
}

