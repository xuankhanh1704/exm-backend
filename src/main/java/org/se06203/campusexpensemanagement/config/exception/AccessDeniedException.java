package org.se06203.campusexpensemanagement.config.exception;


import org.se06203.campusexpensemanagement.config.ErrorCode;

public class AccessDeniedException extends ServerException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}