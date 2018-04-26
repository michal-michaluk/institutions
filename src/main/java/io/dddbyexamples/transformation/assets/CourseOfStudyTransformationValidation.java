package io.dddbyexamples.transformation.assets;

import io.dddbyexamples.transformation.ongoing.AssetTransformationValidation;

import java.util.Set;

class CourseOfStudyTransformationValidation implements AssetTransformationValidation<CourseOfStudy> {

    public boolean validateMove(CourseOfStudy asset,
                                Set<CourseOfStudy> from,
                                Set<CourseOfStudy> to) {
        return true;
    }
}
