package com.nar.util;

import org.springframework.validation.ObjectError;

import java.util.List;

public class AjaxResponse {

    private String message;

    private List<? extends ObjectError> errors;

    private String redirectUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<? extends ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<? extends ObjectError> errors) {
        this.errors = errors;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
