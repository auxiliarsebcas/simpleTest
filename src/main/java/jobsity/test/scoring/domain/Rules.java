package jobsity.test.scoring.domain;

import cyclops.control.Try;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import jobsity.test.scoring.domain.valueObjects.SpareBonus;
import jobsity.test.scoring.domain.valueObjects.StrikeBonus;

import java.util.*;
import java.util.stream.Collectors;

public class Rules {

	public static Try<GameResult,Throwable> calculateGameResult(List<ReleaseResult> results) {
		Map<String, List<ReleaseResult>> gamersMap =
		results.stream().collect(Collectors.groupingBy(releaseResult -> releaseResult.gamerId) );

    return
		Try.withCatch( () ->
			gamersMap.keySet()
				.stream()
				.map(gamerId -> {
					List orderedReleseResult =
					gamersMap.get(gamerId).stream()
					.sorted( (release1, release2) -> release1.instant.compareTo(release2.instant) )
						.collect(Collectors.toList());

					List<Frame> frames =	calculateFramesResult( Collections.synchronizedList( orderedReleseResult ) );
					return
						new ScoreResult(
							  gamerId
							, frames
						);
				})
				.collect( Collectors.toList() )
		).<GameResult>map( scoreResults -> new GameResult(results.get(0).gameName, scoreResults) );
	}

	public static Tuple4<Frame, List<ReleaseResult>, Integer, Integer> calculateFrameResult(final List<ReleaseResult> releaseResults, final int head , final int frameCount, final int score) {
		final int nextRelease = head+1;
		if(frameCount==10) {
			final int lastRelease = nextRelease + 1;

			final int totalScore =  score
				+ (releaseResults.get(head).score==10       ?(releaseResults.get(head).score        + StrikeBonus.bonus):releaseResults.get(head).score)
				+ (releaseResults.get(nextRelease).score==10?(releaseResults.get(nextRelease).score + StrikeBonus.bonus):releaseResults.get(nextRelease).score)
				+ (releaseResults.get(lastRelease).score==10?(releaseResults.get(lastRelease).score + StrikeBonus.bonus):releaseResults.get(lastRelease).score);

			return new Tuple4(
				new Frame(
					  frameCount
					, new Tuple3<String,String,String>(
					  releaseResults.get(head).score==10?"x":Integer.toString(releaseResults.get(head).score)
					, releaseResults.get(nextRelease).score==10?"x":Integer.toString(releaseResults.get(nextRelease).score)
					, releaseResults.get(lastRelease).score==10?"x":Integer.toString(releaseResults.get(lastRelease).score)
				  )
					, totalScore
				)
				, releaseResults
				, lastRelease+1
				, totalScore
			);
		}
		else if(releaseResults.get(head).score==10) {
			final int totalScore = score + releaseResults.get(head).score + StrikeBonus.bonus;
			return new Tuple4<>(
				  new Frame(frameCount,new Tuple2<String,String>("x",""), totalScore)
				, releaseResults
				, head+1
				, totalScore
			);
		}
		else {
			if( (releaseResults.get(head).score + releaseResults.get(nextRelease).score) == 10 ) {
				final int totalScore =  score + releaseResults.get(head).score + releaseResults.get(nextRelease).score+ SpareBonus.bonus;
				return new Tuple4(
					  new Frame( frameCount,new Tuple2<String,String>(Integer.toString(releaseResults.get(head).score),"/"), totalScore )
					, releaseResults
					, nextRelease+1
					, totalScore
				);
			}
			else {
				final int totalScore = score + releaseResults.get(head).score + releaseResults.get(nextRelease).score;
				return new Tuple4(
					  new Frame(frameCount,new Tuple2<String,String>( Integer.toString(releaseResults.get(head).score), Integer.toString(releaseResults.get(nextRelease).score) ), totalScore )
					, releaseResults
					, nextRelease+1
					, totalScore
				);
			}
		}
	}

	public static List<Frame> calculateFramesResult(final List<ReleaseResult> releaseResults) {
	  return calculateFramesResult(releaseResults, 0, 1, 0);
	}

	public static List<Frame> calculateFramesResult(List<ReleaseResult> releaseResults, final int head, final int frameCount, final int score){
		if( head<releaseResults.size() ) {
			final  Tuple4<Frame, List<ReleaseResult>, Integer, Integer> calculusResutl =
				calculateFrameResult(releaseResults, head, frameCount,score);

			//TODO it should be immutable, this is a wrong implementation
			List<Frame> l = (new ArrayList());
			l.add( calculusResutl._1 );
			l.addAll( calculateFramesResult(calculusResutl._2, calculusResutl._3, frameCount+1, calculusResutl._4) );
			return l;
		}
		else return new ArrayList<>();
	}


}
