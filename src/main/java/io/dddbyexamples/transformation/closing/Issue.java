package io.dddbyexamples.transformation.closing;

import io.dddbyexamples.transformation.AssetKey;
import lombok.Value;

@Value
public class Issue {
    AssetKey key;
    String message;
}
