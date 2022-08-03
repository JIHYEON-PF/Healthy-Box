package com.pf.healthybox.domain.articleInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAiReply is a Querydsl query type for AiReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAiReply extends EntityPathBase<AiReply> {

    private static final long serialVersionUID = -867783937L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAiReply aiReply = new QAiReply("aiReply");

    public final com.pf.healthybox.domain.config.QAuditingFields _super = new com.pf.healthybox.domain.config.QAuditingFields(this);

    public final QAiReplyPk aiReplyPk;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath writer = createString("writer");

    public QAiReply(String variable) {
        this(AiReply.class, forVariable(variable), INITS);
    }

    public QAiReply(Path<? extends AiReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAiReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAiReply(PathMetadata metadata, PathInits inits) {
        this(AiReply.class, metadata, inits);
    }

    public QAiReply(Class<? extends AiReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aiReplyPk = inits.isInitialized("aiReplyPk") ? new QAiReplyPk(forProperty("aiReplyPk")) : null;
    }

}

