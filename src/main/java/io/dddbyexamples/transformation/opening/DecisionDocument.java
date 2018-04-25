package io.dddbyexamples.transformation.opening;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DecisionDocument {
    LocalDate decisionDate;
    LocalDate executionDate;
}
