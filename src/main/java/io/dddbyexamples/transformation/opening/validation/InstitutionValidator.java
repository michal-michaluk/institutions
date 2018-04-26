package io.dddbyexamples.transformation.opening.validation;

import io.dddbyexamples.transformation.opening.Institution;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstitutionValidator {

    private final List<Institution.Kind> CAN_BE_TRANSFORMED = Lists.newArrayList(Institution.Kind.PUBLIC, Institution.Kind.NOT_PUBLIC);


    public Object validate(Object institutions) {
        return null;
    }

    public ValidationResult validateStatus(Institution.Status status) {
        switch (status) {
            case OPEN:
                return ValidationResult.ok();
            case LIQUIDATED:
            case TRANSFORMED:
            case IN_LIQUIDATION:
            case IN_TRANSFORMATION:
                return ValidationResult.noOk("Instytucja nie jest aktywna");
            default:
                throw new RuntimeException("Nieznany status: " + status);
        }
    }

    public ValidationResult validateKind(List<Institution.Kind> institutionsKind) {
        Map<String, List<Institution.Kind>> map = institutionsKind.stream()
                .collect(Collectors.groupingBy(Institution.Kind::name));

        if(map.size() == 1) {
            return ValidationResult.ok();
        }
        return ValidationResult.noOk("niemożna połączyć instytucji publicznej i niepublicznej");
    }

    public ValidationResult isKindCantBeTransformed(Institution.Kind institutionKind) {
        if(CAN_BE_TRANSFORMED.contains(institutionKind)){
            return ValidationResult.ok();
        }
        return ValidationResult.noOk("Rodzaj instytucji nie pozwala na przekształcenie");
    }
}
