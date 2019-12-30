
package jobsity.test.infrastructure.configuration;

import jobsity.test.infrastructure.file.FileReaderConfiguration;
import org.junit.Assert;
import org.junit.Test;
import static com.shazam.shazamcrest.matcher.Matchers.*;

import static org.junit.Assert.assertEquals;

public class ScoringConfigurationTest {
    @Test
    public void testAppHasAGreeting() {
        ScoringConfiguration config = new ScoringConfiguration();

      assertEquals("configuration should read service name", "scoring", config.serviceName);
	    Assert.assertThat("configuration should read file reader configuration"
		    , config.fileReaderConfiguration
		    , sameBeanAs(new FileReaderConfiguration("~/tmp", "gameResult")));
    }
}
