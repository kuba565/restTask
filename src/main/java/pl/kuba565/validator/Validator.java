package pl.kuba565.validator;

import org.springframework.validation.Errors;

public interface Validator {
    Errors validateOnDelete(Long id);
}
