package com.pf.healthybox.domain.baseInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBiTerms is a Querydsl query type for BiTerms
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBiTerms extends EntityPathBase<BiTerms> {

    private static final long serialVersionUID = -2050889448L;

    public static final QBiTerms biTerms = new QBiTerms("biTerms");

    public final StringPath code = createString("code");

    public final StringPath content = createString("content");

    public final StringPath termsName = createString("termsName");

    public QBiTerms(String variable) {
        super(BiTerms.class, forVariable(variable));
    }

    public QBiTerms(Path<? extends BiTerms> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBiTerms(PathMetadata metadata) {
        super(BiTerms.class, metadata);
    }

}

