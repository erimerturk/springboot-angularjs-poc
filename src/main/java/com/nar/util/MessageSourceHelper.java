package com.nar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSourceHelper.class);

    @Autowired
    private MessageSource messageSource;

    public String enumToMessageKey(Enum<?> enumValue) {
        return enumValue.getDeclaringClass().getSimpleName() + "." + enumValue.name();
    }

    public String getMessage(String messageKey) {
        return getMessage(messageKey, new Object[]{});
    }

    public String getMessage(String messageKey, Object[] args) {
        return getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

    public String getMessage(String messageKey, Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(messageKey, args, locale);
        } catch (Exception e) {
            LOGGER.warn("No such message with key '" + messageKey + "'", e);
            return "?" + messageKey + "?";
        }
    }

}
