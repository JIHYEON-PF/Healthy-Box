package com.pf.healthybox.domain.orderInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOiDeliver is a Querydsl query type for OiDeliver
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOiDeliver extends EntityPathBase<OiDeliver> {

    private static final long serialVersionUID = 1534337008L;

    public static final QOiDeliver oiDeliver = new QOiDeliver("oiDeliver");

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final EnumPath<com.pf.healthybox.domain.config.DeliveryFlag> deliveryFlag = createEnum("deliveryFlag", com.pf.healthybox.domain.config.DeliveryFlag.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath userId = createString("userId");

    public final StringPath zipcode = createString("zipcode");

    public QOiDeliver(String variable) {
        super(OiDeliver.class, forVariable(variable));
    }

    public QOiDeliver(Path<? extends OiDeliver> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOiDeliver(PathMetadata metadata) {
        super(OiDeliver.class, metadata);
    }

}

