package io.dddbyexamples.transformation.closing;

import io.dddbyexamples.transformation.AssetKey;
import io.dddbyexamples.transformation.TransformationEvents;
import io.dddbyexamples.transformation.ongoing.AssetsDistributed;
import io.dddbyexamples.transformation.ongoing.CloseAsset;
import io.dddbyexamples.transformation.ongoing.MoveAsset;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class TransformingCourseOfStudy {

    public static final String ASSET_KIND = "CourseOfStudy";

    private final String id;
    private final Map<AssetKey, CloseAsset> toClose = new HashMap<>();
    private final Map<AssetKey, MoveAsset> toMove = new HashMap<>();

    private final TransformationEvents events;

    public List<Issue> perform() {
        List<Issue> issues = new ArrayList<>();
        List<AssetKey> done = new ArrayList<>();

        // closing:
        // move all students from Course Of Study to target one
        // close empty Course Of Study

        // moving:
        // change institution of Course Of Study

        TransformationReport event = new TransformationReport(done, issues);
        apply(event);
        events.emit(event);
        return issues;
    }

    void apply(AssetsDistributed event) {
        event.getToClose().forEach(asset -> toClose.put(asset.getKey(), asset));
        event.getToMove().forEach(asset -> toMove.put(asset.getKey(), asset));
    }

    void apply(TransformationReport event) {
        event.getDone().forEach(toClose::remove);
        event.getDone().forEach(toMove::remove);
    }
}
