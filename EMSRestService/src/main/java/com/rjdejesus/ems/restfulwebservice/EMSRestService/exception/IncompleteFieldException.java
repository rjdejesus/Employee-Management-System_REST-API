package com.rjdejesus.ems.restfulwebservice.EMSRestService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompleteFieldException extends RuntimeException {

    public IncompleteFieldException(String message) {
        super();
    }
}
