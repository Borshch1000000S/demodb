package com.borshch.demodb.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator <Phone, String> { //<сама аннотация без @, тип валидируемого поля>

    private final static String TELEPHONE_REGEXP="^\\+7\\(\\d{3}\\)\\d{7}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        return value != null && value.matches(TELEPHONE_REGEXP);
    }

    @Override
    public void initialize(Phone constraintAnnotation) {

    }
}



//HW String Regex Patterns, попробовать написать