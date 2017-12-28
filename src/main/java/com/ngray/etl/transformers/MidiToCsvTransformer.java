package com.ngray.etl.transformers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.common.io.ByteStreams;
import com.ngray.etl.EtlException;
import com.ngray.etl.Log;
import com.ngray.etl.streamable.ByteArrayStreamable;
import com.ngray.etl.streamable.FileStreamable;
import com.ngray.etl.streamable.Streamable;

public class MidiToCsvTransformer extends Transformer<FileStreamable, Streamable, MidiToCsvTransformer.Configuration> {
	
	private static String MIDICSV = "midicsv";
	
	public MidiToCsvTransformer(Configuration config) {
		super(config);
	}

	@Override
	public Streamable transform(FileStreamable input) throws EtlException {
		Configuration config = getConfiguration();
		try {
			return processMidiFile(input, config.MIDICSV_TOOLS_DIRECTORY + MIDICSV);
		} catch (IOException e) {
			throw new EtlException(e);
		}
	}
	
	private static ByteArrayStreamable processMidiFile(FileStreamable input, String midicsvTool) throws IOException, EtlException {
	
		File tmpFile = File.createTempFile(input.getName().replace(".mid", ".csv"), ".tmp");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
		String outputName = input.getName().replace(".mid", ".csv");
		Process p = new ProcessBuilder()
						.command(midicsvTool, input.getFile().getAbsolutePath(), tmpFile.getAbsolutePath())
						.start();

		String line = null;
		String errorMsg = "";
		boolean isError = false;
		try (BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
			while ((line = error.readLine()) != null) {
				errorMsg += input.getName() + ": " + line;
				isError = true;
			}
		}
		
		if (isError) {
			throw new EtlException(errorMsg);
		}
		
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			Log.getLogger().error(e.getMessage(), e);
		}
		
		FileInputStream tmpStream = new FileInputStream(tmpFile);
	    ByteStreams.copy(tmpStream, outputStream);
		return new ByteArrayStreamable(outputName, new ByteArrayInputStream(outputStream.toByteArray()));
	}
	
	public static class Configuration implements com.ngray.etl.Configuration {
		
		/**
		 * This directory should specify the directory containing the midicsv and csvmidi exes
		 */
		public String MIDICSV_TOOLS_DIRECTORY = null;
	}	
}
