package com.pf.healthybox.domain.baseInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBiPoint is a Querydsl query type for BiPoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBiPoint extends EntityPathBase<BiPoint> {

    private static final long serialVersionUID = -2054294239L;

    public static final QBiPoint biPoint = new QBiPoint("biPoint");

    public final StringPath content = createString("content");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> occurDate = createDateTime("occurDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> occurPoint = createNumber("occurPoint", Integer.class);

    public final StringPath userId = createString("userId");

    public QBiPoint(String variable) {
        super(BiPoint.class, forVariable(variable));
    }

    public QBiPoint(Path<? extends BiPoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBiPoint(PathMetadata metadata) {
        super(BiPoint.class, metadata);
    }

}

