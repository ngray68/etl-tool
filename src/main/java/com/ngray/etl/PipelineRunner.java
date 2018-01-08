package com.ngray.etl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.groovy.control.CompilationFailedException;

import com.ngray.etl.streamable.Streamable;



import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class PipelineRunner {

	public static void main(String[] args) throws ClassNotFoundException, CompilationFailedException, IOException {
		Pipeline.Builder<? extends Streamable, ? extends Streamable> builder = new Pipeline.Builder<>();
		Binding binding = new Binding();
		String groovyFile = getGroovyScriptFile(binding, args);
		binding.setVariable("builder", builder);
		binding.setVariable("pipeline", builder);
		setConfiguration(binding, args);
		GroovyShell shell = new GroovyShell(binding);
	
		shell.evaluate(new File(groovyFile));
		Pipeline<? extends Streamable, ? extends Streamable> pipeline = builder.build();
		pipeline.run();
	}

	private static String getGroovyScriptFile(Binding binding, String[] args) {
		List<String> groovyScripts =Arrays.stream(args).filter(arg -> arg.endsWith(".groovy")).collect(Collectors.toList());
		if (groovyScripts.size() == 0) {
			throw new IllegalArgumentException("A groovy script defining a pipeline has not been specified");
		}
		if (groovyScripts.size() > 1) {
			throw new IllegalArgumentException("Exactly one groovy script must be specified");
		}
		return groovyScripts.get(0);
	}

	private static void setConfiguration(Binding binding, String[] args) {
		Arrays.stream(args).filter(arg -> arg.startsWith("-D"))
						   .forEach(
								   arg -> { 
									   String name = arg.substring(2, arg.indexOf("="));
									   String value = arg.substring(arg.indexOf("=")+1, arg.length());
									   binding.setVariable(name, value);
									   }
								   );
	}

}
