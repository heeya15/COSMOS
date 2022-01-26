package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrivateStudyRoom is a Querydsl query type for PrivateStudyRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrivateStudyRoom extends EntityPathBase<PrivateStudyRoom> {

    private static final long serialVersionUID = -577838134L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrivateStudyRoom privateStudyRoom = new QPrivateStudyRoom("privateStudyRoom");

    public final StringPath privatestudyroomId = createString("privatestudyroomId");

    public final QStudy study;

    public QPrivateStudyRoom(String variable) {
        this(PrivateStudyRoom.class, forVariable(variable), INITS);
    }

    public QPrivateStudyRoom(Path<? extends PrivateStudyRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrivateStudyRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrivateStudyRoom(PathMetadata metadata, PathInits inits) {
        this(PrivateStudyRoom.class, metadata, inits);
    }

    public QPrivateStudyRoom(Class<? extends PrivateStudyRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

