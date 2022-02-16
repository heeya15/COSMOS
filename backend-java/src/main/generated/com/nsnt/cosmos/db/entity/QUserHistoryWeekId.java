package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistoryWeekId is a Querydsl query type for UserHistoryWeekId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUserHistoryWeekId extends BeanPath<UserHistoryWeekId> {

    private static final long serialVersionUID = 1573068079L;

    public static final QUserHistoryWeekId userHistoryWeekId = new QUserHistoryWeekId("userHistoryWeekId");

    public final StringPath user_id = createString("user_id");

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public final DatePath<java.time.LocalDate> week_date = createDate("week_date", java.time.LocalDate.class);

    public QUserHistoryWeekId(String variable) {
        super(UserHistoryWeekId.class, forVariable(variable));
    }

    public QUserHistoryWeekId(Path<? extends UserHistoryWeekId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistoryWeekId(PathMetadata metadata) {
        super(UserHistoryWeekId.class, metadata);
    }

}

