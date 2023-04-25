package com.assaf.StoreApi.hendlers.custom;

public class AllFieldRequiredException extends RuntimeException{

    public AllFieldRequiredException() {
    }

    public AllFieldRequiredException(String message) {
        super(message);
    }

    public AllFieldRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllFieldRequiredException(Throwable cause) {
        super(cause);
    }

    public AllFieldRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
