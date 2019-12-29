package jobsity.test.scoring.domain;

import java.util.List;

public class ScoreResult {
	public final String gamerId;
	public final List<Frame> frames;

	public ScoreResult(String gamerName, List<Frame> frames){
		this.gamerId = gamerName;
		this.frames    = frames;
	}
}
