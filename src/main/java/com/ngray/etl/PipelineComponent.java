package com.ngray.etl;

public abstract class PipelineComponent<ConfigurationType extends Configuration> {

	private final ConfigurationType configuration;
	
	public PipelineComponent() {
		this.configuration = null;
	}
	
	public PipelineComponent(final ConfigurationType configuration) {
		this.configuration = configuration;
	}
	
	public ConfigurationType getConfiguration() {
		return configuration;
	}
}
