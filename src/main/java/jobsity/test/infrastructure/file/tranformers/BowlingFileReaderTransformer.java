package jobsity.test.infrastructure.file.tranformers;

import cyclops.control.Either;
import cyclops.control.Try;
import cyclops.data.NonEmptyList;
import io.vavr.Tuple2;
import jobsity.test.ServiceError;
import jobsity.test.infrastructure.file.dtos.LineResult;
import jobsity.test.infrastructure.file.validations.FieldValidations;
import jobsity.test.scoring.domain.ReleaseResult;
import jobsity.test.scoring.domain.errors.ExternalError;

import java.time.Instant;

public class BowlingFileReaderTransformer {
	public static Either<NonEmptyList<ServiceError>, ReleaseResult> lineresultToReleaseResult(String gameName, LineResult lineResult) {
		//TODO define a better structure for the file's records
		return
		Try.<Tuple2<String,String>,Throwable>withCatch(() -> {
			  final String[] fields = lineResult.lineResult.split(" ");
				return
					new Tuple2<String, String>(fields[0], fields[1]);
		}).toEither()
			.<NonEmptyList<ServiceError>>mapLeft(error -> NonEmptyList.of(new ExternalError(error) ) )
			.flatMap( fields ->
				fieldToString( fields._1, "gamerName")
					.flatMap( gamerName ->
						fieldToInteger(fields._2, "score")
							.map(score -> new ReleaseResult(gameName, gamerName, 0, score, Instant.now() ) )
				)
		);
	}

	public static Either<NonEmptyList<ServiceError>, String> fieldToString(String field, String fieldName) {
		return
			FieldValidations.fieldShouldExist(field,fieldName)
				.toEither()
			.mapLeft(error -> NonEmptyList.of( new ExternalError( new Exception(error.head()) ) ) );
	}

	public static Either<NonEmptyList<ServiceError>, Integer> fieldToInteger(String field, String fieldName) {
		return
			FieldValidations.fieldShouldExist(field,fieldName).toEither()
				.flatMap( r ->
					FieldValidations.fieldShouldBeANumber(field, fieldName).toEither()
					.flatMap( number ->
						FieldValidations.fieldShouldBePositiveNumber(Integer.parseInt(number),fieldName).toEither()
					)
				).mapLeft(error -> NonEmptyList.of( (new ExternalError( (new Exception(error.head())) ) ) ) );
	}

}


