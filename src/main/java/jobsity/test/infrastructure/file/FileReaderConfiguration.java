package jobsity.test.infrastructure.file;

public class FileReaderConfiguration {
	public final String path;
	public final String fileName;

	public FileReaderConfiguration(String path, String fileName){
		this.path     = path;
		this.fileName = fileName;
	}
}
