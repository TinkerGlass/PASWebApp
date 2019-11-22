package com.jaba.webapp.exceptions;

public class ApplicationException extends Exception {
    public static enum ErrorCode {
        USERNAME_NOT_UNIQUE
    }

    protected int errorCode;
    protected String errorMessageProperty;

    public ApplicationException(int code, String errorMessageProperty) {
        this.errorMessageProperty = errorMessageProperty;
        this.errorCode = code;
    }

    public ApplicationException(ErrorCode code) {
        this.errorCode = code.ordinal();
        switch(code) {
            case USERNAME_NOT_UNIQUE:
                this.errorMessageProperty="exceptions.usernameNotUnique";
                break;
        }
    }

    public String getErrorMessageProperty() {
        return errorMessageProperty;
    }

    public void setErrorMessageProperty(String errorMessageProperty) {
        this.errorMessageProperty = errorMessageProperty;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
