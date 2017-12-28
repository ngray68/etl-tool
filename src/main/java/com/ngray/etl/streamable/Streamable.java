package com.ngray.etl.streamable;

import java.io.IOException;
import java.io.InputStream;

public interface Streamable {
	
	public String getName();
	public InputStream getStream() throws IOException;

}
