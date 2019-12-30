package jobsity.test.scoring.repositories;

import cyclops.control.Either;
import cyclops.data.NonEmptyList;
import jobsity.test.ServiceError;
import jobsity.test.infrastructure.file.dtos.LineResult;
import jobsity.test.scoring.domain.ReleaseResult;

import java.util.stream.Stream;

public interface BowlingGameResultRepository {
	//TODO it should be a future instead a either or a Eithert with future
	//TODO it should be a ReleaseResult instead a LineResult
	public Either<NonEmptyList<ServiceError>,Stream<LineResult>> getGameResult(String gameId);
}
