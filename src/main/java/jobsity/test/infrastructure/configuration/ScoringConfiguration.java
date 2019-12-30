package jobsity.test.infrastructure.configuration;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import jobsity.test.infrastructure.file.FileReaderConfiguration;


public class ScoringConfiguration {
    public final String serviceName;
    public final FileReaderConfiguration fileReaderConfiguration;

    public ScoringConfiguration() {
        Config conf = ConfigFactory.load();

        this.serviceName = conf.getString("serviceName");

        this.fileReaderConfiguration = new FileReaderConfiguration(
            conf.getString("file_reader.path")
          , conf.getString("file_reader.file_name")
	    );
    }
}
