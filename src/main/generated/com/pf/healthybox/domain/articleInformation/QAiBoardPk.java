package com.pf.healthybox.domain.articleInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAiBoardPk is a Querydsl query type for AiBoardPk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAiBoardPk extends BeanPath<AiBoardPk> {

    private static final long serialVersionUID = -1759265194L;

    public static final QAiBoardPk aiBoardPk = new QAiBoardPk("aiBoardPk");

    public final EnumPath<com.pf.healthybox.domain.config.BoardCategory> boardCategory = createEnum("boardCategory", com.pf.healthybox.domain.config.BoardCategory.class);

    public final EnumPath<com.pf.healthybox.domain.config.BoardGroup> boardGroup = createEnum("boardGroup", com.pf.healthybox.domain.config.BoardGroup.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public QAiBoardPk(String variable) {
        super(AiBoardPk.class, forVariable(variable));
    }

    public QAiBoardPk(Path<? extends AiBoardPk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAiBoardPk(PathMetadata metadata) {
        super(AiBoardPk.class, metadata);
    }

}

