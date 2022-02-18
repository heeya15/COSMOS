package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyType is a Querydsl query type for StudyType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyType extends EntityPathBase<StudyType> {

    private static final long serialVersionUID = -644492038L;

    public static final QStudyType studyType = new QStudyType("studyType");

    public final StringPath studytypeName = createString("studytypeName");

    public final NumberPath<Integer> studytypeNo = createNumber("studytypeNo", Integer.class);

    public QStudyType(String variable) {
        super(StudyType.class, forVariable(variable));
    }

    public QStudyType(Path<? extends StudyType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyType(PathMetadata metadata) {
        super(StudyType.class, metadata);
    }

}

