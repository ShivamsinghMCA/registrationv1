package com.api.registration.exception;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
