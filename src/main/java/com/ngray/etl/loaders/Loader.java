package com.ngray.etl.loaders;

import java.util.Iterator;

import com.ngray.etl.Configuration;
import com.ngray.etl.EtlException;
import com.ngray.etl.PipelineComponent;
import com.ngray.etl.streamable.Streamable;

public abstract class Loader<StreamableType extends Streamable, ConfigurationType extends Configuration> extends PipelineComponent<ConfigurationType> {

	public Loader(final ConfigurationType configuration) {
		super(configuration);
	}
	
	public abstract void load(Iterator<StreamableType> output) throws EtlException; 
}
