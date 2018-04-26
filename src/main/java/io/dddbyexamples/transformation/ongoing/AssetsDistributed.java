package io.dddbyexamples.transformation.ongoing;

import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class AssetsDistributed {
    String kind;
    List<CloseAsset> toClose;
    List<MoveAsset> toMove;

    public static AssetsDistributed close(String kind, List<CloseAsset> toClose) {
        return new AssetsDistributed(kind, Collections.unmodifiableList(toClose), Collections.emptyList());
    }

    public static AssetsDistributed moved(String kind, List<MoveAsset> toMove) {
        return new AssetsDistributed(kind, Collections.emptyList(), Collections.unmodifiableList(toMove));
    }
}
