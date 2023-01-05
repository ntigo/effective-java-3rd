package com.coopnc.effectivejava3rd.item09.exam02;

import com.coopnc.effectivejava3rd.item09.BaseFileWriter;

import java.io.IOException;

public class FileDataWriter extends BaseFileWriter {
	public FileDataWriter() {
	}

	public void close() throws IOException {
		if ( fileWriter != null ) {
			fileWriter.close();
			System.out.println( "file close end" );
		}
	}
}
