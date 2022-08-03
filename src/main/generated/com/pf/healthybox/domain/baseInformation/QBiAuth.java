package com.pf.healthybox.domain.baseInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBiAuth is a Querydsl query type for BiAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBiAuth extends EntityPathBase<BiAuth> {

    private static final long serialVersionUID = -1313634313L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBiAuth biAuth = new QBiAuth("biAuth");

    public final StringPath authName = createString("authName");

    public final QBiAuthPk biAuthPk;

    public final StringPath deleteProd = createString("deleteProd");

    public final StringPath manageBoard = createString("manageBoard");

    public final StringPath manageOrder = createString("manageOrder");

    public final StringPath manageUser = createString("manageUser");

    public final StringPath registerProd = createString("registerProd");

    public final StringPath updatedProd = createString("updatedProd");

    public QBiAuth(String variable) {
        this(BiAuth.class, forVariable(variable), INITS);
    }

    public QBiAuth(Path<? extends BiAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBiAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBiAuth(PathMetadata metadata, PathInits inits) {
        this(BiAuth.class, metadata, inits);
    }

    public QBiAuth(Class<? extends BiAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.biAuthPk = inits.isInitialized("biAuthPk") ? new QBiAuthPk(forProperty("biAuthPk")) : null;
    }

}

