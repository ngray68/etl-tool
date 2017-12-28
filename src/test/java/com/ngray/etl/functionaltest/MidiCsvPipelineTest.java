package com.ngray.etl.functionaltest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ngray.etl.Pipeline;
import com.ngray.etl.extractors.FileSystemExtractor;
import com.ngray.etl.loaders.FileSystemLoader;
import com.ngray.etl.streamable.FileStreamable;
import com.ngray.etl.streamable.Streamable;
import com.ngray.etl.transformers.MidiToCsvTransformer;

public class MidiCsvPipelineTest {

	Pipeline<FileStreamable, Streamable> pipeline;
	
	@Before
	public void setUp() throws Exception {
		
		FileSystemExtractor.Configuration extractorConfig = new FileSystemExtractor.Configuration();
		extractorConfig.SOURCE_DIRECTORY = "/Users/nigelgray/Documents/Music/MIDI/bach/wtcbki/";
		
		FileSystemLoader.Configuration loaderConfig = new FileSystemLoader.Configuration();
		loaderConfig.TARGET_DIRECTORY = "target/temp/";
		
		MidiToCsvTransformer.Configuration midicsvConfig = new MidiToCsvTransformer.Configuration();
		midicsvConfig.MIDICSV_TOOLS_DIRECTORY = "/usr/local/bin/";
		
		pipeline = new Pipeline.Builder<FileStreamable,  Streamable>()
				               .setExtractor(new FileSystemExtractor(extractorConfig))
				               .setLoader(new FileSystemLoader(loaderConfig))
				               .setTransformer(new MidiToCsvTransformer(midicsvConfig))
				               .build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		pipeline.run();
	}

}
