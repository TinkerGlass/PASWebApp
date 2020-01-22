package com.jaba.webapp.exceptions;

public class ApplicationException extends RuntimeException {
    public static enum ErrorCode {
        USERNAME_NOT_UNIQUE, ITEM_ID_DOESNT_EXIST, USER_ID_DOESNT_EXIST, ITEM_UNAVAILABLE, ITEM_AVAILABLE, USER_INACTIVE,
        ITEM_ID_ALREADY_EXISTS
    }

    protected int errorCode;
    protected String errorMessageProperty;
    protected ErrorCode reason;

    public ApplicationException(int code, String errorMessageProperty) {
        this.errorMessageProperty = errorMessageProperty;
        this.errorCode = code;
    }

    public ApplicationException(ErrorCode code) {
        this.errorCode = code.ordinal();
        this.reason = code;
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
            case ITEM_AVAILABLE:
                this.errorMessageProperty="exceptions.itemAvailable";
                break;
            case USER_INACTIVE:
                this.errorMessageProperty="exceptions.userInactive";
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

    public ErrorCode getReason() {
        return reason;
    }

    public void setReason(ErrorCode reason) {
        this.reason = reason;
    }
}
