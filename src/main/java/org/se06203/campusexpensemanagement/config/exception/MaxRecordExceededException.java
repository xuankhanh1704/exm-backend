package org.se06203.campusexpensemanagement.config.exception;


import org.se06203.campusexpensemanagement.config.ErrorCode;

public class MaxRecordExceededException extends ServerException {
    public MaxRecordExceededException() {
        super(ErrorCode.WARNING_LIMIT_RECORD);
    }
}
