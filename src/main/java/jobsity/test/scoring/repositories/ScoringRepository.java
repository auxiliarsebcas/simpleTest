package jobsity.test.scoring.repositories;

import cyclops.control.Either;
import cyclops.data.NonEmptyList;
import cyclops.monads.Witness;
import cyclops.monads.transformers.EitherT;
import jobsity.test.ServiceError;
import jobsity.test.scoring.domain.GameResult;
import jobsity.test.scoring.domain.ReleaseResult;

import java.util.List;


public interface ScoringRepository {
    public Either<NonEmptyList<ServiceError>, ReleaseResult> saveProcessReleaseResult(ReleaseResult releaseResult);

    public Either<NonEmptyList<ServiceError>, List<ReleaseResult>> getProcessReleaseResultByGameName(String gameName);

    public Either<NonEmptyList<ServiceError>, GameResult> saveGameresult(GameResult gameResult);

    public Either<NonEmptyList<ServiceError>, GameResult> getGameresultByGameName(String gameName);
}
