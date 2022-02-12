package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHistoryMonth is a Querydsl query type for UserHistoryMonth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHistoryMonth extends EntityPathBase<UserHistoryMonth> {

    private static final long serialVersionUID = 734552448L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHistoryMonth userHistoryMonth = new QUserHistoryMonth("userHistoryMonth");

    public final NumberPath<Long> totalTime = createNumber("totalTime", Long.class);

    public final QUserHistoryMonthId userHistoryMonthId;

    public QUserHistoryMonth(String variable) {
        this(UserHistoryMonth.class, forVariable(variable), INITS);
    }

    public QUserHistoryMonth(Path<? extends UserHistoryMonth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHistoryMonth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHistoryMonth(PathMetadata metadata, PathInits inits) {
        this(UserHistoryMonth.class, metadata, inits);
    }

    public QUserHistoryMonth(Class<? extends UserHistoryMonth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userHistoryMonthId = inits.isInitialized("userHistoryMonthId") ? new QUserHistoryMonthId(forProperty("userHistoryMonthId")) : null;
    }

}

