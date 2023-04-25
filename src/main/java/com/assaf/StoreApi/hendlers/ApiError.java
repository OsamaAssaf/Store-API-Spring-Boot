package com.assaf.StoreApi.hendlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
class ApiError {
    private HttpStatus status;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message_en;
    private String message_ar;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
    }

    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message_en = "Unexpected error";
        this.message_ar = "خطأ غير متوقع";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message_en,String message_ar, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message_en = message_en;
        this.message_ar = message_ar;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
