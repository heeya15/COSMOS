package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicMember is a Querydsl query type for PublicMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPublicMember extends EntityPathBase<PublicMember> {

    private static final long serialVersionUID = -245714260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicMember publicMember = new QPublicMember("publicMember");

    public final BooleanPath attenacne = createBoolean("attenacne");

    public final NumberPath<Integer> memberScore = createNumber("memberScore", Integer.class);

    public final NumberPath<Integer> publicmemberNo = createNumber("publicmemberNo", Integer.class);

    public final QPublicStudyRoom publicStudyRoom;

    public final QUser user;

    public QPublicMember(String variable) {
        this(PublicMember.class, forVariable(variable), INITS);
    }

    public QPublicMember(Path<? extends PublicMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicMember(PathMetadata metadata, PathInits inits) {
        this(PublicMember.class, metadata, inits);
    }

    public QPublicMember(Class<? extends PublicMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.publicStudyRoom = inits.isInitialized("publicStudyRoom") ? new QPublicStudyRoom(forProperty("publicStudyRoom"), inits.get("publicStudyRoom")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

