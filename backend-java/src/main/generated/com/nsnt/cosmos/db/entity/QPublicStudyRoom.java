package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicStudyRoom is a Querydsl query type for PublicStudyRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPublicStudyRoom extends EntityPathBase<PublicStudyRoom> {

    private static final long serialVersionUID = -451740430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicStudyRoom publicStudyRoom = new QPublicStudyRoom("publicStudyRoom");

    public final StringPath image = createString("image");

    public final NumberPath<Integer> numberOfMember = createNumber("numberOfMember", Integer.class);

    public final StringPath publicstudyroomId = createString("publicstudyroomId");

    public final StringPath studyName = createString("studyName");

    public final StringPath studyRule = createString("studyRule");

    public final QStudyType studyType;

    public final StringPath url = createString("url");

    public QPublicStudyRoom(String variable) {
        this(PublicStudyRoom.class, forVariable(variable), INITS);
    }

    public QPublicStudyRoom(Path<? extends PublicStudyRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicStudyRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicStudyRoom(PathMetadata metadata, PathInits inits) {
        this(PublicStudyRoom.class, metadata, inits);
    }

    public QPublicStudyRoom(Class<? extends PublicStudyRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyType = inits.isInitialized("studyType") ? new QStudyType(forProperty("studyType")) : null;
    }

}

