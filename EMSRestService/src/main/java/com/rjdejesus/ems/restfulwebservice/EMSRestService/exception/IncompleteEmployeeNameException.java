package com.rjdejesus.ems.restfulwebservice.EMSRestService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompleteEmployeeNameException extends RuntimeException {

    public IncompleteEmployeeNameException(String message) {
        super(message);
    }
}
