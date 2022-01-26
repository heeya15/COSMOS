package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrivateMember is a Querydsl query type for PrivateMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrivateMember extends EntityPathBase<PrivateMember> {

    private static final long serialVersionUID = -804521260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrivateMember privateMember = new QPrivateMember("privateMember");

    public final NumberPath<Integer> privatememberNo = createNumber("privatememberNo", Integer.class);

    public final QPrivateStudyRoom privateStudyRoom;

    public final StringPath userId = createString("userId");

    public QPrivateMember(String variable) {
        this(PrivateMember.class, forVariable(variable), INITS);
    }

    public QPrivateMember(Path<? extends PrivateMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrivateMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrivateMember(PathMetadata metadata, PathInits inits) {
        this(PrivateMember.class, metadata, inits);
    }

    public QPrivateMember(Class<? extends PrivateMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.privateStudyRoom = inits.isInitialized("privateStudyRoom") ? new QPrivateStudyRoom(forProperty("privateStudyRoom"), inits.get("privateStudyRoom")) : null;
    }

}

