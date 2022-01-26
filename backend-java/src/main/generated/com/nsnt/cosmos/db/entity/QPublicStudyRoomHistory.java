package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicStudyRoomHistory is a Querydsl query type for PublicStudyRoomHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPublicStudyRoomHistory extends EntityPathBase<PublicStudyRoomHistory> {

    private static final long serialVersionUID = 405695298L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicStudyRoomHistory publicStudyRoomHistory = new QPublicStudyRoomHistory("publicStudyRoomHistory");

    public final DateTimePath<java.time.LocalDateTime> publicroomCloseTime = createDateTime("publicroomCloseTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> publicroomOpenTime = createDateTime("publicroomOpenTime", java.time.LocalDateTime.class);

    public final QPublicStudyRoom publicStudyRoom;

    public final NumberPath<Integer> publicstudyroomhistoryNo = createNumber("publicstudyroomhistoryNo", Integer.class);

    public QPublicStudyRoomHistory(String variable) {
        this(PublicStudyRoomHistory.class, forVariable(variable), INITS);
    }

    public QPublicStudyRoomHistory(Path<? extends PublicStudyRoomHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicStudyRoomHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicStudyRoomHistory(PathMetadata metadata, PathInits inits) {
        this(PublicStudyRoomHistory.class, metadata, inits);
    }

    public QPublicStudyRoomHistory(Class<? extends PublicStudyRoomHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.publicStudyRoom = inits.isInitialized("publicStudyRoom") ? new QPublicStudyRoom(forProperty("publicStudyRoom"), inits.get("publicStudyRoom")) : null;
    }

}

