package com.ngray.etl.transformers;

import com.ngray.etl.EtlException;
import com.ngray.etl.Log;
import com.ngray.etl.streamable.FileStreamable;
import com.ngray.etl.streamable.Streamable;

/**
 * Transformer implementation that does nothing the the input. The method transform(input) simply
 * returns the value it receives unchanged.
 * @author nigelgray
 *
 */
public class IdentityTransformer extends Transformer<FileStreamable, Streamable, IdentityTransformer.Configuration> {

	public IdentityTransformer() {
	}
	
	public IdentityTransformer(Configuration configuration) {
		super(configuration);
	}

	@Override
	public Streamable transform(FileStreamable input) throws EtlException {
		Log.getLogger().info("IdentityTransformer: transforming " + input.getName());
		return input;
	}

	public static class Configuration implements com.ngray.etl.Configuration {
		
	}
}
