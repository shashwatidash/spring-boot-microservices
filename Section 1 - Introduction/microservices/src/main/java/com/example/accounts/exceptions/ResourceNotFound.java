package com.example.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s source not found with given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}