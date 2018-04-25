package io.dddbyexamples.transformation;

import io.dddbyexamples.transformation.opening.TransformationOpened;

public interface TransformationEvents {
    void emit(TransformationOpened event);
}
