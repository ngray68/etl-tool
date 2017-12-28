package com.ngray.etl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ngray.etl.extractors.Extractor;
import com.ngray.etl.loaders.Loader;
import com.ngray.etl.streamable.Streamable;
import com.ngray.etl.transformers.Transformer;

public class Pipeline<InputType extends Streamable, OutputType extends Streamable> {

	public static class Builder<InputType extends Streamable, OutputType extends Streamable> {
		
		private Loader<OutputType, ? extends Configuration> loader;
		private Extractor<InputType, ? extends Configuration> extractor;
		private Transformer<InputType, OutputType, ? extends Configuration> transformer;
		
		public Builder() {
		}
		
		public <C extends Configuration> Builder<InputType, OutputType> setLoader(Loader<OutputType, C> loader) {
			this.loader = loader;
			return this;
		}
		
		public <C extends Configuration> Builder<InputType, OutputType> setExtractor(Extractor<InputType, C> extractor) {
			this.extractor = extractor;
			return this;
		}
		
		public <C extends Configuration> Builder<InputType, OutputType> setTransformer(Transformer<InputType, OutputType, C> transformer) {
			this.transformer = transformer;
			return this;
		}

		public Loader<OutputType, ? extends Configuration> getLoader() {
			return loader;
		}

		public Extractor<InputType, ? extends Configuration> getExtractor() {
			return extractor;
		}

		public Transformer<InputType, OutputType, ? extends Configuration> getTransformer() {
			return transformer;
		}
		
		public Pipeline<InputType, OutputType> build() {
			return new Pipeline<InputType, OutputType>(getExtractor(), getLoader(), getTransformer());
		}
	}

	private final Extractor<InputType, ? extends Configuration> extractor;
	private final Loader<OutputType, ? extends Configuration> loader;
	private final Transformer<InputType, OutputType, ? extends Configuration> transformer;
	
	private Pipeline(Extractor<InputType, ? extends Configuration> extractor,
			Loader<OutputType, ? extends Configuration> loader,
		    Transformer<InputType, OutputType, ? extends Configuration> transformer) {
		this.extractor = extractor;
		this.loader = loader;
		this.transformer = transformer;
	}

	public void run() {	
		try {
			Log.getLogger().info("Running ETL pipeline...");
			Iterator<InputType> inputs = extractor.extract();
			
			List<OutputType> outputs = new ArrayList<OutputType>();
			inputs.forEachRemaining(input -> {
				try {
					outputs.add(transformer.transform(input));
				} catch (EtlException e) {
					Log.getLogger().error(e.getMessage(), e);
				}
			});
			
			loader.load(outputs.iterator());
			
		} catch (EtlException e) {
			Log.getLogger().fatal("Fatal error - stopping ETL pipeline.", e);
		}
	}

}
