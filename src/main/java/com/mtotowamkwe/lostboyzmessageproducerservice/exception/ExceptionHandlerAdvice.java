package com.mtotowamkwe.lostboyzmessageproducerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(MessageNotEnqueuedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String messageNotSentHandler(MessageNotEnqueuedException mnsex) {
        return mnsex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadRestTemplateUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRestTemplateUrlHandler(BadRestTemplateUrlException brtue) {
        return brtue.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DequeuedMessageWasNotEmailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRestTemplateUrlHandler(DequeuedMessageWasNotEmailedException dmwnex) {
        return dmwnex.getMessage();
    }
}
