package io.dddbyexamples.transformation.ongoing;

import lombok.Value;

import java.util.Set;

@Value
public class KnownAssetsChanged {
    String kind;
    Set<String> newAssets;
    Set<String> goneAssets;
}
