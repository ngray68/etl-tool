package com.ngray.etl.loaders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import com.google.common.io.ByteStreams;
import com.ngray.etl.EtlException;
import com.ngray.etl.Log;
import com.ngray.etl.streamable.Streamable;

public class FileSystemLoader extends Loader<Streamable, FileSystemLoader.Configuration> {

	public FileSystemLoader(Configuration configuration) {
		super(configuration);
	}

	@Override
	public void load(Iterator<Streamable> outputs) throws EtlException {
		Configuration config = getConfiguration();	
		if (config.TARGET_DIRECTORY == null) {
			throw new EtlException("No target directory specified for FileSystemLoader");
		}
		
		Log.getLogger().info("Loading files to directory " + config.TARGET_DIRECTORY);
		
		File sourceDirectory = new File(config.TARGET_DIRECTORY);
		if (!sourceDirectory.isDirectory()) {
			throw new EtlException("Target directory " + config.TARGET_DIRECTORY + " specified for FileSystemLoader is not a directory");
		}
		outputs.forEachRemaining(output -> 
				{
					File file = new File(getConfiguration().TARGET_DIRECTORY + output.getName());
					Log.getLogger().debug("Loading file " + file.getName());
					try (InputStream inputStream = output.getStream();
					    FileOutputStream outputStream = new FileOutputStream(file)) {
						ByteStreams.copy(output.getStream(), outputStream);
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			);
	}
	
	public static class Configuration implements com.ngray.etl.Configuration {
		public String TARGET_DIRECTORY = null;
	}

}
