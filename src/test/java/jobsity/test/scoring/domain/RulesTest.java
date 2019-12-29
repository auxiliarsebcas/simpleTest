
package jobsity.test.scoring.domain;

import cyclops.control.Option;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static com.shazam.shazamcrest.matcher.Matchers.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RulesTest {

    @Test
    public void testCalculateGameResult() {
	    List<jobsity.test.scoring.domain.ReleaseResult> resutls = Arrays.asList(
		    new Tuple2("Jeff",	"10")
		    , new Tuple2("Jeff",	"7")
		    , new Tuple2("Jeff",	"3")
		    , new Tuple2("Jeff",	"9")
		    , new Tuple2("Jeff",	"0")
		    , new Tuple2("Jeff",	"10")
		    , new Tuple2("Jeff",	"0")
		    , new Tuple2("Jeff",	"8")
		    , new Tuple2("Jeff",	"8")
		    , new Tuple2("Jeff",	"2")
		    , new Tuple2("Jeff",	"0")
		    , new Tuple2("Jeff",	"6")
		    , new Tuple2("Jeff",	"10")
		    , new Tuple2("Jeff",	"10")
		    , new Tuple2("Jeff",	"10")
		    , new Tuple2("Jeff",	"10")
		    , new Tuple2("Jeff",	"10")
	    ).stream()
		    .map(t -> new jobsity.test.scoring.domain.ReleaseResult("gameTest", (String) t._1, 1, Integer.valueOf((String)  t._2), Instant.now() ) )
		    .collect(Collectors.toList());

    	Option<GameResult> result =
	     (new jobsity.test.scoring.domain.Rules()).calculateGameResult(resutls)
		    .get();

	    List<Frame> expectedFrames = new ArrayList<>();
      expectedFrames.add(new Frame(1, new Tuple2<>("x", "") , 20) );
	    expectedFrames.add(new Frame(2, new Tuple2<>("7", "/") , 40) );
	    expectedFrames.add(new Frame(3, new Tuple2<>("9", "0") , 49) );
	    expectedFrames.add(new Frame(4, new Tuple2<>("x", "") , 69) );
	    expectedFrames.add(new Frame(5, new Tuple2<>("0", "8") , 77) );
	    expectedFrames.add(new Frame(6, new Tuple2<>("8", "/") , 97) );
	    expectedFrames.add(new Frame(7, new Tuple2<>("0", "6") , 103) );
	    expectedFrames.add(new Frame(8, new Tuple2<>("x", "") , 123) );
	    expectedFrames.add(new Frame(9, new Tuple2<>("x", "") , 143) );
	    expectedFrames.add(new Frame(10, new Tuple3<>("x", "x", "x") , 203) );

	    List<ScoreResult> expectedScoreResult = new ArrayList<>();
	     expectedScoreResult
		     .add( new ScoreResult("Jeff", expectedFrames	) );

	     Option<GameResult> expected =
		    Option.of(
		      new GameResult(
		      	"gameTest", expectedScoreResult )
		    );

	    Assert.assertThat("calculateGameResult should calculate a gameResult from a normal game"
		    , result
		    , sameBeanAs(expected)
	    );

    }

	@Test
	public void testCalculateGameResultWithAllZero() {
		List<jobsity.test.scoring.domain.ReleaseResult> resutls = Arrays.asList(
			  new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
			, new Tuple2("Jeff",	"0")
		).stream()
			.map(t -> new jobsity.test.scoring.domain.ReleaseResult("gameTest", (String) t._1, 1, Integer.valueOf((String)  t._2), Instant.now() ) )
			.collect(Collectors.toList());

		Option<GameResult> result =
			(new jobsity.test.scoring.domain.Rules()).calculateGameResult(resutls)
				.get();

		List<Frame> expectedFrames = new ArrayList<>();
		expectedFrames.add(new Frame(1, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(2, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(3, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(4, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(5, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(6, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(7, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(8, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(9, new Tuple2<>("0", "0") , 0) );
		expectedFrames.add(new Frame(10, new Tuple3<>("0", "0", "0") , 0) );

		List<ScoreResult> expectedScoreResult = new ArrayList<>();
		expectedScoreResult
			.add( new ScoreResult("Jeff", expectedFrames	) );

		Option<GameResult> expected =
			Option.of(
				new GameResult(
					"gameTest", expectedScoreResult )
			);

		Assert.assertThat("calculateGameResult should calculate a gameResult if all pinfalls were 0"
			, result
			, sameBeanAs(expected)
		);

	}

	@Test
	public void testCalculateGameResultWithAllTen() {
		List<jobsity.test.scoring.domain.ReleaseResult> resutls = Arrays.asList(
			new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
			, new Tuple2("Jeff",	"10")
		).stream()
			.map(t -> new jobsity.test.scoring.domain.ReleaseResult("gameTest", (String) t._1, 1, Integer.valueOf((String)  t._2), Instant.now() ) )
			.collect(Collectors.toList());

		Option<GameResult> result =
			(new jobsity.test.scoring.domain.Rules()).calculateGameResult(resutls)
				.get();

		List<Frame> expectedFrames = new ArrayList<>();
		expectedFrames.add(new Frame(1, new Tuple2<>("x", "") , 20) );
		expectedFrames.add(new Frame(2, new Tuple2<>("x", "") , 40) );
		expectedFrames.add(new Frame(3, new Tuple2<>("x", "") , 60) );
		expectedFrames.add(new Frame(4, new Tuple2<>("x", "") , 80) );
		expectedFrames.add(new Frame(5, new Tuple2<>("x", "") , 100) );
		expectedFrames.add(new Frame(6, new Tuple2<>("x", "") , 120) );
		expectedFrames.add(new Frame(7, new Tuple2<>("x", "") , 140) );
		expectedFrames.add(new Frame(8, new Tuple2<>("x", "") , 160) );
		expectedFrames.add(new Frame(9, new Tuple2<>("x", "") , 180) );
		expectedFrames.add(new Frame(10, new Tuple3<>("x", "x", "x") , 240) );

		List<ScoreResult> expectedScoreResult = new ArrayList<>();
		expectedScoreResult
			.add( new ScoreResult("Jeff", expectedFrames	) );

		Option<GameResult> expected =
			Option.of(
				new GameResult("gameTest", expectedScoreResult )
			);

		Assert.assertThat("calculateGameResult should calculate a gameResult from a normal game"
			, result
			, sameBeanAs(expected)
		);

	}

}
