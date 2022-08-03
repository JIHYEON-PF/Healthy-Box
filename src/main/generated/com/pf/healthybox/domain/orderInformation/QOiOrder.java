package com.pf.healthybox.domain.orderInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOiOrder is a Querydsl query type for OiOrder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOiOrder extends EntityPathBase<OiOrder> {

    private static final long serialVersionUID = 964089049L;

    public static final QOiOrder oiOrder = new QOiOrder("oiOrder");

    public final com.pf.healthybox.domain.config.QAuditingFields _super = new com.pf.healthybox.domain.config.QAuditingFields(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath apiCode = createString("apiCode");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> deliverIdx = createNumber("deliverIdx", Long.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final EnumPath<com.pf.healthybox.domain.config.PayMethod> payMethod = createEnum("payMethod", com.pf.healthybox.domain.config.PayMethod.class);

    public final StringPath productCode = createString("productCode");

    public final NumberPath<Integer> qty = createNumber("qty", Integer.class);

    public final StringPath sellerCode = createString("sellerCode");

    public final EnumPath<com.pf.healthybox.domain.config.Status> status = createEnum("status", com.pf.healthybox.domain.config.Status.class);

    public final NumberPath<Integer> unitCost = createNumber("unitCost", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath userId = createString("userId");

    public QOiOrder(String variable) {
        super(OiOrder.class, forVariable(variable));
    }

    public QOiOrder(Path<? extends OiOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOiOrder(PathMetadata metadata) {
        super(OiOrder.class, metadata);
    }

}

