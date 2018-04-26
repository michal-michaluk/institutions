package io.dddbyexamples.transformation.opening.validation;

import io.dddbyexamples.transformation.opening.InstutuionStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstitutionValidatorEnumTest {

    @Test
    public void validateStatus() throws Exception {
        InstitutionValidator validator = new InstitutionValidator();
        for (InstutuionStatus instutuionStatus : InstutuionStatus.values()) {
            validator.validateStatus(instutuionStatus);
        }
    }

}