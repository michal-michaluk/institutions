package io.dddbyexamples.transformation.opening;

import io.dddbyexamples.transformation.TransformationTypes;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class TransformationOpened {
    private final TransformationTypes type;
    private final DecisionDocument document;
    private final List<String> targetInstitutions;
    private final List<String> transformedInstitutions;
}
