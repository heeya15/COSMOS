package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplyMember is a Querydsl query type for ApplyMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApplyMember extends EntityPathBase<ApplyMember> {

    private static final long serialVersionUID = -824162753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplyMember applyMember = new QApplyMember("applyMember");

    public final NumberPath<Integer> applymemberNo = createNumber("applymemberNo", Integer.class);

    public final QStudy study_no;

    public final QUser userId;

    public QApplyMember(String variable) {
        this(ApplyMember.class, forVariable(variable), INITS);
    }

    public QApplyMember(Path<? extends ApplyMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplyMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplyMember(PathMetadata metadata, PathInits inits) {
        this(ApplyMember.class, metadata, inits);
    }

    public QApplyMember(Class<? extends ApplyMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study_no = inits.isInitialized("study_no") ? new QStudy(forProperty("study_no"), inits.get("study_no")) : null;
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId")) : null;
    }

}

