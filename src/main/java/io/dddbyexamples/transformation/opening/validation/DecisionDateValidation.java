package io.dddbyexamples.transformation.opening.validation;

import io.dddbyexamples.transformation.opening.Institution;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DecisionDateValidation {

    public Object validate(Object decisionDates) {
        return null;
    }

    public ValidationResult checkInstitutionOnDate(List<Institution> institutions, LocalDate checkOnDate) {
        Optional<Institution> institutionStartedAfterCheckDate = institutions.stream()
                .filter(institution -> institution.getEstablished().isAfter(checkOnDate))
                .findAny();

        if (institutionStartedAfterCheckDate.isPresent()) {
            return ValidationResult.noOk("");
        }
        return ValidationResult.ok();
    }
}
