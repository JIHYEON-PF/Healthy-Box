package com.pf.healthybox.domain.productInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPiProduct is a Querydsl query type for PiProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPiProduct extends EntityPathBase<PiProduct> {

    private static final long serialVersionUID = -1377953158L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPiProduct piProduct = new QPiProduct("piProduct");

    public final com.pf.healthybox.domain.config.QAuditingFields _super = new com.pf.healthybox.domain.config.QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath detail = createString("detail");

    public final StringPath isUsed = createString("isUsed");

    public final QPiProductPk piProductPk;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productCategory = createString("productCategory");

    public final StringPath productGroup = createString("productGroup");

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QPiProduct(String variable) {
        this(PiProduct.class, forVariable(variable), INITS);
    }

    public QPiProduct(Path<? extends PiProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPiProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPiProduct(PathMetadata metadata, PathInits inits) {
        this(PiProduct.class, metadata, inits);
    }

    public QPiProduct(Class<? extends PiProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.piProductPk = inits.isInitialized("piProductPk") ? new QPiProductPk(forProperty("piProductPk")) : null;
    }

}

