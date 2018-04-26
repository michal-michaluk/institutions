package io.dddbyexamples.transformation.views.list;

import io.dddbyexamples.transformation.opening.TransformationOpened;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
//Entity
public class TransformationListEntity {

    //Converter to json
    private TransformationOpened opened;

    public TransformationListEntity(TransformationOpened opened) {
        this.opened = opened;
    }
}
