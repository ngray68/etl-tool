import com.ngray.etl.loaders.FileSystemLoader
import com.ngray.etl.transformers.MidiToCsvTransformer
import com.ngray.etl.extractors.FileSystemExtractor

def source = new FileSystemExtractor.Configuration(SOURCE_DIRECTORY : sourceDir)
def target = new FileSystemLoader.Configuration(TARGET_DIRECTORY: targetDir);
def midicsv = new MidiToCsvTransformer.Configuration(MIDICSV_TOOLS_DIRECTORY : midiToolsDir)

builder.setExtractor(new FileSystemExtractor(source))
builder.setTransformer(new MidiToCsvTransformer(midicsv))
builder.setLoader(new FileSystemLoader(target))
