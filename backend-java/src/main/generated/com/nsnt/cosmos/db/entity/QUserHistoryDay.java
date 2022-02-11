package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHistoryDay is a Querydsl query type for UserHistoryDay
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHistoryDay extends EntityPathBase<UserHistoryDay> {

    private static final long serialVersionUID = -1416002916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHistoryDay userHistoryDay = new QUserHistoryDay("userHistoryDay");

    public final NumberPath<Long> totalTime = createNumber("totalTime", Long.class);

    public final QUserHistoryDayId userhistoryDayId;

    public QUserHistoryDay(String variable) {
        this(UserHistoryDay.class, forVariable(variable), INITS);
    }

    public QUserHistoryDay(Path<? extends UserHistoryDay> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHistoryDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHistoryDay(PathMetadata metadata, PathInits inits) {
        this(UserHistoryDay.class, metadata, inits);
    }

    public QUserHistoryDay(Class<? extends UserHistoryDay> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userhistoryDayId = inits.isInitialized("userhistoryDayId") ? new QUserHistoryDayId(forProperty("userhistoryDayId")) : null;
    }

}

