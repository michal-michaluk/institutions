package io.dddbyexamples.transformation.views.list;

public interface TransformationListDao { // extends JpaRepository
    void save(TransformationListEntity transformationListEntity);
}
