package com.huawei.olympicranking.exception;


public class DuplicateMedalException extends RuntimeException {
    public DuplicateMedalException() {
        super("Same athlete + same sport + same medal type already exists");
    }
    
}
