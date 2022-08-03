package com.pf.healthybox.domain.articleInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAiReplyPk is a Querydsl query type for AiReplyPk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAiReplyPk extends BeanPath<AiReplyPk> {

    private static final long serialVersionUID = -716705446L;

    public static final QAiReplyPk aiReplyPk = new QAiReplyPk("aiReplyPk");

    public final StringPath boardCategory = createString("boardCategory");

    public final StringPath boardGroup = createString("boardGroup");

    public final NumberPath<Long> boardIdx = createNumber("boardIdx", Long.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public QAiReplyPk(String variable) {
        super(AiReplyPk.class, forVariable(variable));
    }

    public QAiReplyPk(Path<? extends AiReplyPk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAiReplyPk(PathMetadata metadata) {
        super(AiReplyPk.class, metadata);
    }

}

