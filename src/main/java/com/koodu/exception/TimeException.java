package com.koodu.exception;

/**
 *
 * @author Abiola.Adebanjo
 */
public class TimeException extends Exception {

    ErrorResponse errorResponse;

    public TimeException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorResponse = new ErrorResponse(errorCode, errorMessage);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

}
