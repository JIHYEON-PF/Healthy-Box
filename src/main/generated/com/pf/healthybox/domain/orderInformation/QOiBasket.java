package com.pf.healthybox.domain.orderInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOiBasket is a Querydsl query type for OiBasket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOiBasket extends EntityPathBase<OiBasket> {

    private static final long serialVersionUID = -565437029L;

    public static final QOiBasket oiBasket = new QOiBasket("oiBasket");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath productCode = createString("productCode");

    public final NumberPath<Integer> qty = createNumber("qty", Integer.class);

    public final StringPath sellerCode = createString("sellerCode");

    public final StringPath userId = createString("userId");

    public QOiBasket(String variable) {
        super(OiBasket.class, forVariable(variable));
    }

    public QOiBasket(Path<? extends OiBasket> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOiBasket(PathMetadata metadata) {
        super(OiBasket.class, metadata);
    }

}

