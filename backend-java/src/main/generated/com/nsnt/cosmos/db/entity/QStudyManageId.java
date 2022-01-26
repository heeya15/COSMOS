package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyManageId is a Querydsl query type for StudyManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QStudyManageId extends BeanPath<StudyManageId> {

    private static final long serialVersionUID = -266385632L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyManageId studyManageId = new QStudyManageId("studyManageId");

    public final NumberPath<Long> studyManageNo = createNumber("studyManageNo", Long.class);

    public final QStudy studyNo;

    public QStudyManageId(String variable) {
        this(StudyManageId.class, forVariable(variable), INITS);
    }

    public QStudyManageId(Path<? extends StudyManageId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyManageId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyManageId(PathMetadata metadata, PathInits inits) {
        this(StudyManageId.class, metadata, inits);
    }

    public QStudyManageId(Class<? extends StudyManageId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyNo = inits.isInitialized("studyNo") ? new QStudy(forProperty("studyNo"), inits.get("studyNo")) : null;
    }

}

