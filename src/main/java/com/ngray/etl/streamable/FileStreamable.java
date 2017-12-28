package com.ngray.etl.streamable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileStreamable implements Streamable {

	private final String name;
	private final File file;
	
	public FileStreamable(String name, File file) {
		this.name = name;
		this.file = file;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public InputStream getStream() throws IOException {
		return new FileInputStream(file);
	}
	
	public File getFile() {
		return file;
	}
}
