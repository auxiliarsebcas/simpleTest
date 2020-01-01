package jobsity.test.infrastructure.file.validations;

import cyclops.control.Validated;
import org.apache.commons.lang3.StringUtils;


public class FieldValidations {
	public static Validated<String, String>  fieldShouldExist(String field, String fieldName) {
		if( field == null || field.isEmpty() )
			return Validated.invalid("the field "+ fieldName +" should not be empty or null");
		else
			return Validated.valid(field);
	}

	public static Validated<String, String> fieldShouldBeANumber(String field, String fieldName) {
		if( field.toLowerCase().equals("f") ) return Validated.valid("0");
		else if( StringUtils.isNumeric(field) ) return Validated.valid(field);
		else return Validated.invalid("the field "+fieldName+" should be a number");
	}

	public static Validated<String, Integer> fieldShouldBePositiveNumber(Integer field, String fieldName) {
		if(field < 0 ) return Validated.invalid("the field "+fieldName+" should be positive");
		else return Validated.valid(field);
	}
}
