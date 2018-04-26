package io.dddbyexamples.transformation.ongoing;

import java.util.Set;

public interface AssetTransformationValidation<ASSET> {

    boolean validateMove(ASSET asset, Set<ASSET> from, Set<ASSET> to);
}
