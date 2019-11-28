package com.jaba.webapp.exceptions;

public class ApplicationException extends Exception {
    public static enum ErrorCode {
        USERNAME_NOT_UNIQUE, ITEM_ID_DOESNT_EXIST, USER_ID_DOESNT_EXIST, ITEM_UNAVAILABLE
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
            case ITEM_ID_DOESNT_EXIST:
                this.errorMessageProperty="exceptions.itemIDDoesntExist";
                break;
            case USER_ID_DOESNT_EXIST:
                this.errorMessageProperty="exceptions.userIDDoesntExist";
                break;
            case ITEM_UNAVAILABLE:
                this.errorMessageProperty="exceptions.itemUnavailable";
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
