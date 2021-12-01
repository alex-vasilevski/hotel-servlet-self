package org.bsuir.ivan.infrostructure;

import lombok.Getter;

@Getter
public class NoSuchBeanException extends Exception{

    private String message;

    public NoSuchBeanException(String message) {
        super(message);
        this.message = message;
    }
}
