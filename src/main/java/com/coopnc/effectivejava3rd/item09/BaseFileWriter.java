package com.coopnc.effectivejava3rd.item09;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public abstract class BaseFileWriter {
	protected FileWriter fileWriter;

	public void fileOpen( final String fileName ) throws Exception {
		File file = new File( fileName );
		if ( !file.exists() ) {
			file.createNewFile();
		}

		fileWriter = new FileWriter( file, true );
	}

	public void fileWrite( final String data ) throws Exception {
		if ( fileWriter != null ) {
			fileWriter.write( data );
			fileWriter.write( "\n" );
		}
	}

	public String getTestFilePath() {
		StringBuilder pathBuilder = new StringBuilder( 50 );
		pathBuilder.append( Paths.get( "" ).toAbsolutePath().toString() )
				.append( "\\test.txt" );

		return pathBuilder.toString();
	}
}
