package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBannedUser is a Querydsl query type for BannedUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBannedUser extends EntityPathBase<BannedUser> {

    private static final long serialVersionUID = 934697618L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBannedUser bannedUser = new QBannedUser("bannedUser");

    public final NumberPath<Integer> banneduserNo = createNumber("banneduserNo", Integer.class);

    public final QPublicStudyRoom publicStudyRoom;

    public final StringPath userId = createString("userId");

    public QBannedUser(String variable) {
        this(BannedUser.class, forVariable(variable), INITS);
    }

    public QBannedUser(Path<? extends BannedUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBannedUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBannedUser(PathMetadata metadata, PathInits inits) {
        this(BannedUser.class, metadata, inits);
    }

    public QBannedUser(Class<? extends BannedUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.publicStudyRoom = inits.isInitialized("publicStudyRoom") ? new QPublicStudyRoom(forProperty("publicStudyRoom"), inits.get("publicStudyRoom")) : null;
    }

}

