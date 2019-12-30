package jobsity.test.scoring.commands;

import cyclops.control.Either;
import cyclops.control.Reader;
import cyclops.data.NonEmptyList;
import jobsity.test.ServiceError;
import jobsity.test.scoring.ScoringEnvironment;
import jobsity.test.scoring.domain.Rules;
import jobsity.test.scoring.domain.errors.ApplicationError;
import jobsity.test.scoring.domain.events.Event;
import jobsity.test.scoring.domain.events.GameResultFileProcessed;

import static jobsity.test.infrastructure.file.tranformers.BowlingFileReaderTransformer.lineresultToReleaseResult;

public class ProcessGameResultFile {

	public Reader<ScoringEnvironment, Either<NonEmptyList<ServiceError>,NonEmptyList<Event>>> execute() {
		return Reader.of( environment -> {
			return
			environment.bowlingGameResultRepository.getGameResult("this should be the file name, but this is not a service and it does not make sense to dirty the domain with it ")
				.flatMap( stream -> {
					return
					stream.map( lineResult ->
						lineresultToReleaseResult("and again the game name", lineResult)
						.flatMap(releaseResult -> environment.scoringRepository.saveProcessReleaseResult(releaseResult))
					);
				})
				.flatMap(r -> {
					return
						environment.scoringRepository.getProcessReleaseResultByGameName("the file name")
						.flatMap(releaseResults -> {
							return
								Rules.calculateGameResult(releaseResults).toEither()
								.<NonEmptyList<ServiceError>>mapLeft(error -> NonEmptyList.of( new ApplicationError(error) ) )
								.flatMap(gameResult -> environment.scoringRepository.saveGameresult(gameResult) );
						});
				})
				.map( r -> NonEmptyList.of(new GameResultFileProcessed("") ) )
		});
	}
}
