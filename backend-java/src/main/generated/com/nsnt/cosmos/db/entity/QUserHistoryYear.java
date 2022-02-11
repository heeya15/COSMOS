package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHistoryYear is a Querydsl query type for UserHistoryYear
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHistoryYear extends EntityPathBase<UserHistoryYear> {

    private static final long serialVersionUID = -945788611L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHistoryYear userHistoryYear = new QUserHistoryYear("userHistoryYear");

    public final NumberPath<Long> totalTime = createNumber("totalTime", Long.class);

    public final QUserHistoryYearId userhistoryYearId;

    public QUserHistoryYear(String variable) {
        this(UserHistoryYear.class, forVariable(variable), INITS);
    }

    public QUserHistoryYear(Path<? extends UserHistoryYear> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHistoryYear(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHistoryYear(PathMetadata metadata, PathInits inits) {
        this(UserHistoryYear.class, metadata, inits);
    }

    public QUserHistoryYear(Class<? extends UserHistoryYear> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userhistoryYearId = inits.isInitialized("userhistoryYearId") ? new QUserHistoryYearId(forProperty("userhistoryYearId")) : null;
    }

}

