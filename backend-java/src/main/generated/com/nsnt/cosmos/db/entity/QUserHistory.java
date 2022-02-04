package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistory is a Querydsl query type for UserHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHistory extends EntityPathBase<UserHistory> {

    private static final long serialVersionUID = 1852824800L;

    public static final QUserHistory userHistory = new QUserHistory("userHistory");

    public final DateTimePath<java.time.LocalDateTime> userFinishTime = createDateTime("userFinishTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> userhistoryNo = createNumber("userhistoryNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> userStartTime = createDateTime("userStartTime", java.time.LocalDateTime.class);

    public QUserHistory(String variable) {
        super(UserHistory.class, forVariable(variable));
    }

    public QUserHistory(Path<? extends UserHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistory(PathMetadata metadata) {
        super(UserHistory.class, metadata);
    }

}

