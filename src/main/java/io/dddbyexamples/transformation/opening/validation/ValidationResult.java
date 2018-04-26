package io.dddbyexamples.transformation.opening.validation;

import lombok.Value;

/**
 * Created by apawlak on 25.04.18.
 */
@Value
public class ValidationResult {
    private boolean valid;
    private String message;

    public static ValidationResult ok() {
        return new ValidationResult(true, "");
    }

    public static ValidationResult noOk(String message) {
        return new ValidationResult(false, message);
    }
}
