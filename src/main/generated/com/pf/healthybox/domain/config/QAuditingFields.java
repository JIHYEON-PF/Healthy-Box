package com.pf.healthybox.domain.config;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditingFields is a Querydsl query type for AuditingFields
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditingFields extends EntityPathBase<AuditingFields> {

    private static final long serialVersionUID = -780703785L;

    public static final QAuditingFields auditingFields = new QAuditingFields("auditingFields");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QAuditingFields(String variable) {
        super(AuditingFields.class, forVariable(variable));
    }

    public QAuditingFields(Path<? extends AuditingFields> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditingFields(PathMetadata metadata) {
        super(AuditingFields.class, metadata);
    }

}

