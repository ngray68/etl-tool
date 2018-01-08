package com.ngray.etl.functionaltest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;

public class PipelineRunnerTest {


	@Test
	public void testPipelineRunner() throws IOException {
		
		String cmd = "java -jar /Users/nigelgray/git/repos/etl-tool/target/etl-tool-0.0.1-SNAPSHOT-jar-with-dependencies.jar src/main/resources/groovy-scripts/MidiToCsvPipeline.groovy" +
	                 "-DsourceDir=/Users/nigelgray/Documents/Music/MIDI/bach/wtcbki/" +
	                 "-DtargetDir=target/temp/" +
	                 "-DmidiToolsDir=/usr/local/bin/";
		
		new ProcessBuilder().command(cmd).start();
	}

}
