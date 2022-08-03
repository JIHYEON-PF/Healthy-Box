package com.pf.healthybox.domain.baseInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBiSeller is a Querydsl query type for BiSeller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBiSeller extends EntityPathBase<BiSeller> {

    private static final long serialVersionUID = 818127374L;

    public static final QBiSeller biSeller = new QBiSeller("biSeller");

    public final com.pf.healthybox.domain.config.QAuditingFields _super = new com.pf.healthybox.domain.config.QAuditingFields(this);

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath sellerCode = createString("sellerCode");

    public final StringPath sellerName = createString("sellerName");

    public final StringPath serialCode = createString("serialCode");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath zipcode = createString("zipcode");

    public QBiSeller(String variable) {
        super(BiSeller.class, forVariable(variable));
    }

    public QBiSeller(Path<? extends BiSeller> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBiSeller(PathMetadata metadata) {
        super(BiSeller.class, metadata);
    }

}

