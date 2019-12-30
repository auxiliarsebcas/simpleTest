package jobsity.test.scoring.repositories;

import cyclops.control.Either;
import cyclops.data.NonEmptyList;
import jobsity.test.ServiceError;
import jobsity.test.infrastructure.file.FileReaderConfiguration;
import jobsity.test.infrastructure.file.GowlingGameFileReader;
import jobsity.test.infrastructure.file.dtos.LineResult;
import jobsity.test.scoring.domain.errors.ExternalError;

import java.util.stream.Stream;

public class BowlingGameResultRepositoryImpl implements BowlingGameResultRepository{

	public final FileReaderConfiguration config;

	public BowlingGameResultRepositoryImpl(FileReaderConfiguration config){
		this.config = config;
	}
	//TODO it should be a ReleaseResult instead a LineResult
	@Override
	public Either<NonEmptyList<ServiceError>,Stream<LineResult>> getGameResult(String gameId) {
		return
			(new GowlingGameFileReader()).readFile(config.path,config.fileName) //TODO this is not a good idea, the service should be able to manage different files by request
			.toEither()
			.mapLeft(error -> NonEmptyList.of( new ExternalError(new Error(error) ) ) );
	}
}
