package com.pf.healthybox.domain.articleInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAiBoard is a Querydsl query type for AiBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAiBoard extends EntityPathBase<AiBoard> {

    private static final long serialVersionUID = -882276613L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAiBoard aiBoard = new QAiBoard("aiBoard");

    public final com.pf.healthybox.domain.config.QAuditingFields _super = new com.pf.healthybox.domain.config.QAuditingFields(this);

    public final QAiBoardPk aiBoardPk;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath productCode = createString("productCode");

    public final StringPath sellerCode = createString("sellerCode");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath writer = createString("writer");

    public QAiBoard(String variable) {
        this(AiBoard.class, forVariable(variable), INITS);
    }

    public QAiBoard(Path<? extends AiBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAiBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAiBoard(PathMetadata metadata, PathInits inits) {
        this(AiBoard.class, metadata, inits);
    }

    public QAiBoard(Class<? extends AiBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aiBoardPk = inits.isInitialized("aiBoardPk") ? new QAiBoardPk(forProperty("aiBoardPk")) : null;
    }

}

