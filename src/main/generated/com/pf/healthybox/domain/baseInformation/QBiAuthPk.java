package com.pf.healthybox.domain.baseInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBiAuthPk is a Querydsl query type for BiAuthPk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBiAuthPk extends BeanPath<BiAuthPk> {

    private static final long serialVersionUID = 317812818L;

    public static final QBiAuthPk biAuthPk = new QBiAuthPk("biAuthPk");

    public final StringPath division = createString("division");

    public final StringPath level = createString("level");

    public QBiAuthPk(String variable) {
        super(BiAuthPk.class, forVariable(variable));
    }

    public QBiAuthPk(Path<? extends BiAuthPk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBiAuthPk(PathMetadata metadata) {
        super(BiAuthPk.class, metadata);
    }

}

