package io.dddbyexamples.transformation.ongoing;

import io.dddbyexamples.transformation.AssetKey;
import lombok.Value;

@Value
public class MoveAsset {
    AssetKey key;
    String toInstitution;
}
