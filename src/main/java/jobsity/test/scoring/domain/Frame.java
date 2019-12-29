package jobsity.test.scoring.domain;

import io.vavr.Tuple;

public class Frame<T extends Tuple> {
	public final int frameNumber;
	public final T pinFalls;
	public final int score;

	public Frame(int frameNumber, T pinFalls, int score){
		this.frameNumber = frameNumber;
		this.pinFalls    = pinFalls;
		this.score       = score;
	}
}
