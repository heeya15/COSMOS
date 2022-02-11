package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistoryMonthId is a Querydsl query type for UserHistoryMonthId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUserHistoryMonthId extends BeanPath<UserHistoryMonthId> {

    private static final long serialVersionUID = 1530268347L;

    public static final QUserHistoryMonthId userHistoryMonthId = new QUserHistoryMonthId("userHistoryMonthId");

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final StringPath user_id = createString("user_id");

    public QUserHistoryMonthId(String variable) {
        super(UserHistoryMonthId.class, forVariable(variable));
    }

    public QUserHistoryMonthId(Path<? extends UserHistoryMonthId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistoryMonthId(PathMetadata metadata) {
        super(UserHistoryMonthId.class, metadata);
    }

}

