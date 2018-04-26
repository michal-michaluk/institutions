package io.dddbyexamples.transformation.ongoing;

import io.dddbyexamples.transformation.AssetKey;
import lombok.Value;

@Value
public class CloseAsset {
    AssetKey key;
    AssetKey moveDependentToAsset;

    public String getFromInstitution() {
        return key.getInstitution();
    }

    public String getToInstitution() {
        return key.getInstitution();
    }
}
