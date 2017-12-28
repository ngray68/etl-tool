package com.ngray.etl.extractors;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ngray.etl.EtlException;
import com.ngray.etl.Log;
import com.ngray.etl.streamable.FileStreamable;

public class FileSystemExtractor extends Extractor<FileStreamable, FileSystemExtractor.Configuration> {
	
	public FileSystemExtractor(Configuration configuration) {
		super(configuration);
	}

	
	@Override
	public Iterator<FileStreamable> extract() throws EtlException {
		
		Configuration config = getConfiguration();	
		if (config.SOURCE_DIRECTORY == null) {
			throw new EtlException("No source directory specified for FileSystemExtractor");
		}
		
		Log.getLogger().info("Extracting files from directory " + config.SOURCE_DIRECTORY);
		
		File sourceDirectory = new File(config.SOURCE_DIRECTORY);
		if (!sourceDirectory.isDirectory()) {
			throw new EtlException("Source directory " + config.SOURCE_DIRECTORY + " specified for FileSystemExtractor is not a directory");
		}
		
		File[] files = sourceDirectory.listFiles();
		List<FileStreamable> streamables = new ArrayList<>();
		for (File file : files) {
			Log.getLogger().debug("Extracting file " + file.getName());
			streamables.add(new FileStreamable(file.getName(), file));
		}
		return streamables.iterator();
	}
	
	public static class Configuration implements com.ngray.etl.Configuration {		
		
		/**
		 * The directory from which files are read by the extractor
		 */
		public String SOURCE_DIRECTORY = null;
	}

}
