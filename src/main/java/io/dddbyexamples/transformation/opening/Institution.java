package io.dddbyexamples.transformation.opening;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Institution {
    private InstutuionStatus status;
    private InstitutionKind institutionKind;
    private LocalDate startDate;
}
