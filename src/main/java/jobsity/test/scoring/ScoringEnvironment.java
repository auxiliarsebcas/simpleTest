package jobsity.test.scoring;


import jobsity.test.infrastructure.configuration.ScoringConfiguration;
import jobsity.test.scoring.repositories.BowlingGameResultRepository;
import jobsity.test.scoring.repositories.ScoringRepository;

public class ScoringEnvironment {
	  public final ScoringConfiguration config;
    public final ScoringRepository scoringRepository;
    public final BowlingGameResultRepository bowlingGameResultRepository;

    public ScoringEnvironment(ScoringConfiguration config, ScoringRepository scoringRepository, BowlingGameResultRepository bowlingGameResultRepository){
        this.config                      = config;
        this.scoringRepository           = scoringRepository;
        this.bowlingGameResultRepository = bowlingGameResultRepository;
    }
}
