package io.dddbyexamples.transformation;

import io.dddbyexamples.transformation.closing.TransformationReport;
import io.dddbyexamples.transformation.ongoing.AssetsDistributed;
import io.dddbyexamples.transformation.ongoing.KnownAssetsChanged;
import io.dddbyexamples.transformation.opening.TransformationOpened;

public interface TransformationEvents {
    void emit(TransformationOpened event);
    void emit(KnownAssetsChanged event);
    void emit(AssetsDistributed event);
    void emit(TransformationReport event);
}
