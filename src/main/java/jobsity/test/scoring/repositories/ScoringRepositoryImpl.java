package jobsity.test.scoring.repositories;

import cyclops.control.Either;
import cyclops.data.NonEmptyList;
import cyclops.monads.Witness;
import cyclops.monads.transformers.EitherT;
import jobsity.test.ServiceError;
import jobsity.test.scoring.domain.GameResult;
import jobsity.test.scoring.domain.ReleaseResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoringRepositoryImpl implements ScoringRepository {

	//Note this implementation is just for this test...

	private final Map<String, List<ReleaseResult>> bd = new HashMap<>();
	@Override
	public Either<NonEmptyList<ServiceError>, ReleaseResult> saveProcessReleaseResult(ReleaseResult releaseResult) {
		if(this.bd.containsKey(releaseResult.gameName))
			this.bd.get(releaseResult.gameName).add(releaseResult);
		else{
			List releaseResults = new ArrayList();
			releaseResults.add(releaseResult);
			this.bd.put(releaseResult.gameName, releaseResults );
		}

		return Either.right(releaseResult);
	}

	@Override
	public Either<NonEmptyList<ServiceError>, List<ReleaseResult>> getProcessReleaseResultByGameName(String gameName) {
		return Either.right( this.bd.get(gameName) );
	}

	public Either<NonEmptyList<ServiceError>, GameResult> getGameresultByGameName(String gameName);

}
