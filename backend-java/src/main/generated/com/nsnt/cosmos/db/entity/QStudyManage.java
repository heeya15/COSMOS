package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyManage is a Querydsl query type for StudyManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyManage extends EntityPathBase<StudyManage> {

    private static final long serialVersionUID = -1104186587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyManage studyManage = new QStudyManage("studyManage");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath studymanageNotice = createString("studymanageNotice");

    public final QStudyManageId studymangeId;

    public QStudyManage(String variable) {
        this(StudyManage.class, forVariable(variable), INITS);
    }

    public QStudyManage(Path<? extends StudyManage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyManage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyManage(PathMetadata metadata, PathInits inits) {
        this(StudyManage.class, metadata, inits);
    }

    public QStudyManage(Class<? extends StudyManage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studymangeId = inits.isInitialized("studymangeId") ? new QStudyManageId(forProperty("studymangeId"), inits.get("studymangeId")) : null;
    }

}

