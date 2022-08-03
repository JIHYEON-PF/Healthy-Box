package com.pf.healthybox.domain.productInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPiDivProductPk is a Querydsl query type for PiDivProductPk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPiDivProductPk extends BeanPath<PiDivProductPk> {

    private static final long serialVersionUID = 701888270L;

    public static final QPiDivProductPk piDivProductPk = new QPiDivProductPk("piDivProductPk");

    public final StringPath productCategory = createString("productCategory");

    public final StringPath productGroup = createString("productGroup");

    public QPiDivProductPk(String variable) {
        super(PiDivProductPk.class, forVariable(variable));
    }

    public QPiDivProductPk(Path<? extends PiDivProductPk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPiDivProductPk(PathMetadata metadata) {
        super(PiDivProductPk.class, metadata);
    }

}

