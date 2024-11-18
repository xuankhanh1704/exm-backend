package org.se06203.campusexpensemanagement.config.exception;

import lombok.Getter;
import org.se06203.campusexpensemanagement.config.ErrorCode;

@Getter
public class NotFoundException extends ServerException {

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    public NotFoundException(String errorMessage) {
        super(ErrorCode.NOT_FOUND, errorMessage);
    }

    public NotFoundException(String className, String identifier) {
        super(ErrorCode.NOT_FOUND, className, identifier);
    }
}
