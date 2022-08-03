package com.pf.healthybox.domain.productInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPiProductPk is a Querydsl query type for PiProductPk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPiProductPk extends BeanPath<PiProductPk> {

    private static final long serialVersionUID = -1363055083L;

    public static final QPiProductPk piProductPk = new QPiProductPk("piProductPk");

    public final StringPath productCode = createString("productCode");

    public final StringPath sellerCode = createString("sellerCode");

    public QPiProductPk(String variable) {
        super(PiProductPk.class, forVariable(variable));
    }

    public QPiProductPk(Path<? extends PiProductPk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPiProductPk(PathMetadata metadata) {
        super(PiProductPk.class, metadata);
    }

}

