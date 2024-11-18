package org.se06203.campusexpensemanagement.config.exception;

import lombok.Getter;
import org.se06203.campusexpensemanagement.config.ErrorCode;

@Getter
public class SocialUserNotFoundException extends ServerException {
    public SocialUserNotFoundException(String payload) {
        super(ErrorCode.NOT_FOUND, payload);
    }
}
