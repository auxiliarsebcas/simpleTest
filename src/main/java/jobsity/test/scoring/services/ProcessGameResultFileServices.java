package jobsity.test.scoring.services;

import cyclops.control.Reader;
import jobsity.test.scoring.ScoringEnvironment;
import jobsity.test.scoring.commands.ProcessGameResultFile;

public class ProcessGameResultFileServices {

	public Reader<ScoringEnvironment, String> run() {
		Reader.of(environment -> {
			return
			(new ProcessGameResultFile()).execute().apply(environment)
				//TODO it should be a query following cqrs, then this part should belong to another service and another  endpoint
			.flatMap(environment -> environment.scoringRepository.getGameresultByGameName("fileName"))
				.flatMap(gameResult -> PrintGameResult(gameResult))
		});
	}
}
