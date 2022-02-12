package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHistoryWeek is a Querydsl query type for UserHistoryWeek
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHistoryWeek extends EntityPathBase<UserHistoryWeek> {

    private static final long serialVersionUID = -945848076L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHistoryWeek userHistoryWeek = new QUserHistoryWeek("userHistoryWeek");

    public final NumberPath<Long> totalTime = createNumber("totalTime", Long.class);

    public final QUserHistoryWeekId userHistoryWeekId;

    public QUserHistoryWeek(String variable) {
        this(UserHistoryWeek.class, forVariable(variable), INITS);
    }

    public QUserHistoryWeek(Path<? extends UserHistoryWeek> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHistoryWeek(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHistoryWeek(PathMetadata metadata, PathInits inits) {
        this(UserHistoryWeek.class, metadata, inits);
    }

    public QUserHistoryWeek(Class<? extends UserHistoryWeek> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userHistoryWeekId = inits.isInitialized("userHistoryWeekId") ? new QUserHistoryWeekId(forProperty("userHistoryWeekId")) : null;
    }

}

