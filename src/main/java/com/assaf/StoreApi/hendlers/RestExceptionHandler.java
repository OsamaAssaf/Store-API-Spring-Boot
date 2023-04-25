package com.assaf.StoreApi.hendlers;

import com.assaf.StoreApi.hendlers.custom.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        String error_en = "Malformed JSON request";
        String error_ar = "Malformed JSON request";
        return buildResponseEntity(new ApiError(BAD_REQUEST, error_en,error_ar, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex){
        ApiError apiError = new ApiError(FORBIDDEN);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AllFieldRequiredException.class)
    protected ResponseEntity<Object> handleAllFieldRequiredException(
            AllFieldRequiredException ex){
        ApiError apiError = new ApiError(BAD_REQUEST);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IdRequiredException.class)
    protected ResponseEntity<Object> handleIdRequiredException(
            IdRequiredException ex){
        ApiError apiError = new ApiError(BAD_REQUEST);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    protected ResponseEntity<Object> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex){
        ApiError apiError = new ApiError(BAD_REQUEST);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    protected ResponseEntity<Object> handleEmailNotFoundException(
            EmailNotFoundException ex){
        ApiError apiError = new ApiError(NOT_FOUND);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ImageNotAddedException.class)
    protected ResponseEntity<Object> handleImageNotAddedException(
            ImageNotAddedException ex){
        ApiError apiError = new ApiError(NOT_FOUND);
        String[] messages = ex.getMessage().split("\\|");
        apiError.setMessage_en(messages[0]);
        apiError.setMessage_ar(messages[1]);
        return buildResponseEntity(apiError);
    }
}
