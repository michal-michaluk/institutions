package io.dddbyexamples.transformation.views.list;

import io.dddbyexamples.transformation.ongoing.AssetsDistributed;
import io.dddbyexamples.transformation.opening.TransformationOpened;
import lombok.AllArgsConstructor;

//Component
@AllArgsConstructor
public class TransformationListProjection {

    /// spring data repo
    private TransformationListDao dao;

    public void apply(TransformationOpened event) {
        dao.save(new TransformationListEntity(event));
    }

    public void apply(AssetsDistributed event) {
        // TransformationListEntity entity = dao.find();
        // entity.
    }
}
