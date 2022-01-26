package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHistoryId is a Querydsl query type for UserHistoryId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUserHistoryId extends BeanPath<UserHistoryId> {

    private static final long serialVersionUID = -1846792677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHistoryId userHistoryId = new QUserHistoryId("userHistoryId");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final QUser user;

    public final NumberPath<Integer> userhistoryNo = createNumber("userhistoryNo", Integer.class);

    public QUserHistoryId(String variable) {
        this(UserHistoryId.class, forVariable(variable), INITS);
    }

    public QUserHistoryId(Path<? extends UserHistoryId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHistoryId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHistoryId(PathMetadata metadata, PathInits inits) {
        this(UserHistoryId.class, metadata, inits);
    }

    public QUserHistoryId(Class<? extends UserHistoryId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

