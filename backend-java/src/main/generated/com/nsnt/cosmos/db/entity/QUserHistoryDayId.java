package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistoryDayId is a Querydsl query type for UserHistoryDayId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUserHistoryDayId extends BeanPath<UserHistoryDayId> {

    private static final long serialVersionUID = 725832919L;

    public static final QUserHistoryDayId userHistoryDayId = new QUserHistoryDayId("userHistoryDayId");

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final StringPath user_id = createString("user_id");

    public QUserHistoryDayId(String variable) {
        super(UserHistoryDayId.class, forVariable(variable));
    }

    public QUserHistoryDayId(Path<? extends UserHistoryDayId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistoryDayId(PathMetadata metadata) {
        super(UserHistoryDayId.class, metadata);
    }

}

