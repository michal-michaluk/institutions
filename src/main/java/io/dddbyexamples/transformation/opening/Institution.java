package io.dddbyexamples.transformation.opening;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Institution {
    private Status status;
    private Kind kind;
    private LocalDate established;

    public enum Kind {

        PUBLIC,
        NOT_PUBLIC,
        ECCLESIASTICAL,
        SCIENTIST
    }

    public enum Status {

        OPEN,
        LIQUIDATED,
        IN_LIQUIDATION,
        IN_TRANSFORMATION,
        TRANSFORMED
    }
}
