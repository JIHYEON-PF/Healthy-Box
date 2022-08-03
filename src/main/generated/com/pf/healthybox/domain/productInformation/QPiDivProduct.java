package com.pf.healthybox.domain.productInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPiDivProduct is a Querydsl query type for PiDivProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPiDivProduct extends EntityPathBase<PiDivProduct> {

    private static final long serialVersionUID = 809668019L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPiDivProduct piDivProduct = new QPiDivProduct("piDivProduct");

    public final StringPath categoryName = createString("categoryName");

    public final StringPath groupName = createString("groupName");

    public final StringPath isUsed = createString("isUsed");

    public final QPiDivProductPk piDivProductPk;

    public QPiDivProduct(String variable) {
        this(PiDivProduct.class, forVariable(variable), INITS);
    }

    public QPiDivProduct(Path<? extends PiDivProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPiDivProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPiDivProduct(PathMetadata metadata, PathInits inits) {
        this(PiDivProduct.class, metadata, inits);
    }

    public QPiDivProduct(Class<? extends PiDivProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.piDivProductPk = inits.isInitialized("piDivProductPk") ? new QPiDivProductPk(forProperty("piDivProductPk")) : null;
    }

}

