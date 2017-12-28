package com.ngray.etl.transformers;

import com.ngray.etl.Configuration;
import com.ngray.etl.EtlException;
import com.ngray.etl.PipelineComponent;
import com.ngray.etl.streamable.Streamable;

public abstract class Transformer<InputType extends Streamable, OutputType extends Streamable, ConfigurationType extends Configuration> extends PipelineComponent<ConfigurationType> {

	public Transformer() {
	}
	
	public Transformer(ConfigurationType configuration) {
		super(configuration);
	}
	
	public abstract OutputType transform(InputType input) throws EtlException;;
}
