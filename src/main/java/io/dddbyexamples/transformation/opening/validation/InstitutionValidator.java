package io.dddbyexamples.transformation.opening.validation;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import io.dddbyexamples.transformation.opening.InstitutionKind;
import io.dddbyexamples.transformation.opening.InstutuionStatus;
import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstitutionValidator {

    private final List<InstitutionKind> CAN_BE_TRANSFORMED = Lists.newArrayList(InstitutionKind.PUBLIC, InstitutionKind.NOT_PUBLIC);


    public Object validate(Object institutions) {
        return null;
    }

    public ValidationResult validateStatus(InstutuionStatus status) {
        switch (status) {
            case OPENING:
                return ValidationResult.ok();
            case LIQUIDATED:
            case TRANSFORMED:
            case IN_LIQUIDATED:
            case IN_TRANSFORMATION:
                return ValidationResult.noOk("Instytucja nie jest aktywna");
            default:
                throw new RuntimeException("Nieznany status: " + status);
        }
    }

    public ValidationResult validateKind(List<InstitutionKind> institutionsKind) {
        Map<String, List<InstitutionKind>> map = institutionsKind.stream()
                .collect(Collectors.groupingBy(InstitutionKind::name));

        if(map.size() == 1) {
            return ValidationResult.ok();
        }
        return ValidationResult.noOk("niemożna połączyć instytucji publicznej i niepublicznej");
    }

    public ValidationResult isKindCantBeTransformed(InstitutionKind institutionKind) {
        if(CAN_BE_TRANSFORMED.contains(institutionKind)){
            return ValidationResult.ok();
        }
        return ValidationResult.noOk("Rodzaj instytucji nie pozwala na przekształcenie");
    }
}
