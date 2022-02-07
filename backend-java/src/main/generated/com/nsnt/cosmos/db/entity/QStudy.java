package com.nsnt.cosmos.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = 2002231712L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath image = createString("image");

    public final NumberPath<Integer> numberOfMember = createNumber("numberOfMember", Integer.class);

    public final StringPath studyName = createString("studyName");

    public final NumberPath<Long> studyNo = createNumber("studyNo", Long.class);

    public final StringPath studyPassword = createString("studyPassword");

    public final StringPath studyRule = createString("studyRule");

    public final QStudyType studyType;

    public final NumberPath<Integer> totalMember = createNumber("totalMember", Integer.class);

    public final StringPath url = createString("url");

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy(PathMetadata metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyType = inits.isInitialized("studyType") ? new QStudyType(forProperty("studyType")) : null;
    }

}

