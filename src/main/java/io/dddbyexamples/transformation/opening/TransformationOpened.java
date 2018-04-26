package io.dddbyexamples.transformation.opening;

import io.dddbyexamples.transformation.TransformationTypes;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class TransformationOpened {
    TransformationTypes type;
    DecisionDocument document;
    List<String> targetInstitutions;
    List<String> transformedInstitutions;
}
