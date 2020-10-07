package pl.kuba565.resttask.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationException extends RuntimeException {
    public ValidationException(List<ObjectError> errors) {
        super(buildErrorMessage(errors));
    }

    private static String buildErrorMessage(List<ObjectError> errors) {
        StringBuilder errorMessageSB = new StringBuilder();
        errors.forEach(error -> errorMessageSB.append(error.getCode()).append(System.lineSeparator()));
        return errorMessageSB.toString();
    }
}
