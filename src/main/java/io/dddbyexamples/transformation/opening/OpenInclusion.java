package io.dddbyexamples.transformation.opening;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class OpenInclusion {
    DecisionDocument document;
    String targetInstitution;
    List<String> transformedInstitutions;
}
