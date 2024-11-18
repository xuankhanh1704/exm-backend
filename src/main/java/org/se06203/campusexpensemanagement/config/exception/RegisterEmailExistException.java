package org.se06203.campusexpensemanagement.config.exception;


import org.se06203.campusexpensemanagement.config.ErrorCode;

public class RegisterEmailExistException extends ServerException {
    public RegisterEmailExistException() {
        super(ErrorCode.EMAIL_EXISTED);
    }
}
