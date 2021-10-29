package frontend.controller;

import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Annotation;

public class AuthToken implements RequestHeader {
    @Override
    public String value() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public String defaultValue() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
