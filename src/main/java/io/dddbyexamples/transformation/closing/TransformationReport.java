package io.dddbyexamples.transformation.closing;

import io.dddbyexamples.transformation.AssetKey;
import lombok.Value;

import java.util.List;

@Value
public class TransformationReport {
    List<AssetKey> done;
    List<Issue> issues;
}
