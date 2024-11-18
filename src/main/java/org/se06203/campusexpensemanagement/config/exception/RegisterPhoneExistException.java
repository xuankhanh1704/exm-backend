package org.se06203.campusexpensemanagement.config.exception;


import org.se06203.campusexpensemanagement.config.ErrorCode;

public class RegisterPhoneExistException extends ServerException {
    public RegisterPhoneExistException() {
        super(ErrorCode.PHONE_EXISTED);
    }
}
