package jobsity.test.infrastructure.file;

import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import cyclops.control.Try;
import jobsity.test.infrastructure.file.dtos.LineResult;

import java.io.File;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GowlingGameFileReader {

	public Try<Stream<LineResult>,Throwable> readFile(String path, String fileName) {
		return
		Try.withCatch( () -> {
			return
			Files.lines(
				  Paths.get(path+ File.separator+fileName)
				, StandardCharsets.UTF_8
			).map(lineReaded -> new LineResult(lineReaded)); //TODO this map should not be here, ti should be a back pressure, the stream lost his worth with this map.
		});
	}

}
