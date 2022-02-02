package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1311572244L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> totalAttendance = createNumber("totalAttendance", Integer.class);

    public final NumberPath<Integer> totalTime = createNumber("totalTime", Integer.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPassword = createString("userPassword");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

