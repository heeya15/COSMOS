package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrivateStudyRoomHistory is a Querydsl query type for PrivateStudyRoomHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrivateStudyRoomHistory extends EntityPathBase<PrivateStudyRoomHistory> {

    private static final long serialVersionUID = -1549688470L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrivateStudyRoomHistory privateStudyRoomHistory = new QPrivateStudyRoomHistory("privateStudyRoomHistory");

    public final DateTimePath<java.time.LocalDateTime> privateroomCloseTime = createDateTime("privateroomCloseTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> privateroomOpenTime = createDateTime("privateroomOpenTime", java.time.LocalDateTime.class);

    public final QPrivateStudyRoom privateStudyRoom;

    public final NumberPath<Integer> privatestudyroomhistoryNo = createNumber("privatestudyroomhistoryNo", Integer.class);

    public QPrivateStudyRoomHistory(String variable) {
        this(PrivateStudyRoomHistory.class, forVariable(variable), INITS);
    }

    public QPrivateStudyRoomHistory(Path<? extends PrivateStudyRoomHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrivateStudyRoomHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrivateStudyRoomHistory(PathMetadata metadata, PathInits inits) {
        this(PrivateStudyRoomHistory.class, metadata, inits);
    }

    public QPrivateStudyRoomHistory(Class<? extends PrivateStudyRoomHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.privateStudyRoom = inits.isInitialized("privateStudyRoom") ? new QPrivateStudyRoom(forProperty("privateStudyRoom"), inits.get("privateStudyRoom")) : null;
    }

}

