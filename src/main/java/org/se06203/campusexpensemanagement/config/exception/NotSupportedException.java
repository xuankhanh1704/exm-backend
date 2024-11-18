package org.se06203.campusexpensemanagement.config.exception;

import lombok.Getter;
import org.se06203.campusexpensemanagement.config.ErrorCode;

@Getter
public class NotSupportedException extends ServerException {
    public NotSupportedException() {
        super(ErrorCode.NOT_SUPPORTED);
    }
}
