package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistoryYearId is a Querydsl query type for UserHistoryYearId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUserHistoryYearId extends BeanPath<UserHistoryYearId> {

    private static final long serialVersionUID = 1630213944L;

    public static final QUserHistoryYearId userHistoryYearId = new QUserHistoryYearId("userHistoryYearId");

    public final StringPath user_id = createString("user_id");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QUserHistoryYearId(String variable) {
        super(UserHistoryYearId.class, forVariable(variable));
    }

    public QUserHistoryYearId(Path<? extends UserHistoryYearId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistoryYearId(PathMetadata metadata) {
        super(UserHistoryYearId.class, metadata);
    }

}

