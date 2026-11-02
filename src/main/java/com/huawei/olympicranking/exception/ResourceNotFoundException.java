package com.huawei.olympicranking.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String className) {
        super("The resource " + className + " not found in the system.");
    }
    
}
