package org.se06203.campusexpensemanagement.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.se06203.campusexpensemanagement.config.ErrorCode;
import org.se06203.campusexpensemanagement.config.exception.ServerException;
import org.se06203.campusexpensemanagement.utils.Constants;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.isBlank(phoneNumber) && !phoneNumber.matches(Constants.PHONE_REGEX)) {
            throw new ServerException(ErrorCode.INVALID_PHONE_NUMBER, "phone number is not valid");
        }
        return true;
    }
}
