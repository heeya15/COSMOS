package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyMember is a Querydsl query type for StudyMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyMember extends EntityPathBase<StudyMember> {

    private static final long serialVersionUID = -1100521382L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyMember studyMember = new QStudyMember("studyMember");

    public final BooleanPath attendance = createBoolean("attendance");

    public final BooleanPath authority = createBoolean("authority");

    public final BooleanPath leader = createBoolean("leader");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final QStudy study;

    public final NumberPath<Long> studymemberNo = createNumber("studymemberNo", Long.class);

    public final NumberPath<Integer> studytime = createNumber("studytime", Integer.class);

    public final QUser user;

    public QStudyMember(String variable) {
        this(StudyMember.class, forVariable(variable), INITS);
    }

    public QStudyMember(Path<? extends StudyMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyMember(PathMetadata metadata, PathInits inits) {
        this(StudyMember.class, metadata, inits);
    }

    public QStudyMember(Class<? extends StudyMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

