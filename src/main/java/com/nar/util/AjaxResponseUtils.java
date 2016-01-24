package com.nar.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.util.List;

@Component
public class AjaxResponseUtils {

    @Autowired
    private MessageSourceHelper messageSourceHelper;

    public AjaxResponseBuilder success() {
        return ajaxResponseBuilderInstance(HttpStatus.OK);
    }

    public AjaxResponseBuilder redirectInternal() {
        return ajaxResponseBuilderInstance(HttpStatus.SEE_OTHER);
    }

    public AjaxResponseBuilder redirectExternal() {
        return ajaxResponseBuilderInstance(HttpStatus.MOVED_PERMANENTLY);
    }

    public AjaxResponseBuilder unProcessable() {
        return ajaxResponseBuilderInstance(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private AjaxResponseBuilder ajaxResponseBuilderInstance(HttpStatus httpStatus) {
        return new AjaxResponseBuilder(httpStatus);
    }

    public class AjaxResponseBuilder {

        private HttpStatus status;

        private String message;

        private List<? extends ObjectError> errors;

        private String messageKey;

        private Object[] args = new Object[]{};

        private String redirectUrl;

        public AjaxResponseBuilder(HttpStatus status) {
            this.status = status;
        }

        public ResponseEntity<AjaxResponse> build() {
            AjaxResponse ajaxResponse = new AjaxResponse();
            ajaxResponse.setRedirectUrl(redirectUrl);
            ajaxResponse.setErrors(errors);
            ajaxResponse.setMessage(StringUtils.isNotBlank(messageKey) ? messageSourceHelper.getMessage(messageKey, args) : message);
            return new ResponseEntity<>(ajaxResponse, status);
        }

        public AjaxResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public AjaxResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public AjaxResponseBuilder errors(List<? extends ObjectError> errors) {
            this.errors = errors;
            return this;
        }

        public AjaxResponseBuilder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public AjaxResponseBuilder messageKey(String messageKey) {
            this.messageKey = messageKey;
            return this;
        }

        public AjaxResponseBuilder args(Object[] args) {
            this.args = args;
            return this;
        }

    }

}
