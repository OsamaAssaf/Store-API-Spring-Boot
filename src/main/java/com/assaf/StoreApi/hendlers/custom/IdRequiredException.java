package com.assaf.StoreApi.hendlers.custom;

public class IdRequiredException extends RuntimeException{
    public IdRequiredException() {
    }

    public IdRequiredException(String message) {
        super(message);
    }

    public IdRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdRequiredException(Throwable cause) {
        super(cause);
    }

    public IdRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
