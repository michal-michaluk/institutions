package io.dddbyexamples.transformation.opening.validation;

import io.dddbyexamples.transformation.opening.Institution;
import org.junit.Test;

public class InstitutionValidatorEnumTest {

    @Test
    public void validateStatus() throws Exception {
        InstitutionValidator validator = new InstitutionValidator();
        for (Institution.Status instutuionStatus : Institution.Status.values()) {
            validator.validateStatus(instutuionStatus);
        }
    }

}