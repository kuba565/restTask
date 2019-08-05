package pl.kuba565.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationException extends RuntimeException {
    public ValidationException(List<ObjectError> errors) {
        super(addErrorsToMessage(errors));
    }

    private static String addErrorsToMessage(List<ObjectError> errors) {
        StringBuilder errorMessageSB = new StringBuilder();
        errors.forEach(error -> errorMessageSB.append(error.getCode()));
        return errorMessageSB.toString();
    }
}
