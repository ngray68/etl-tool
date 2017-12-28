package com.ngray.etl.streamable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayStreamable implements Streamable {

	private String name;
	private ByteArrayInputStream stream;
	
	public ByteArrayStreamable(String name, ByteArrayInputStream stream) {
		this.name = name;
		this.stream = stream;
	}
	
	public ByteArrayStreamable() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public InputStream getStream() throws IOException {
		return stream;
	}

}
