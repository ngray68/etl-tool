package com.ngray.etl.extractors;

import java.util.Iterator;

import com.ngray.etl.Configuration;
import com.ngray.etl.EtlException;
import com.ngray.etl.PipelineComponent;
import com.ngray.etl.streamable.Streamable;

public abstract class Extractor<StreamableType extends Streamable, ConfigurationType extends Configuration> extends PipelineComponent<ConfigurationType> {

	public Extractor(final ConfigurationType configuration) {
		super(configuration);
	}
	
	public abstract Iterator<StreamableType> extract() throws EtlException;
}
