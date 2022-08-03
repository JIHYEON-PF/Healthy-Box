package com.pf.healthybox.domain.baseInformation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBiUser is a Querydsl query type for BiUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBiUser extends EntityPathBase<BiUser> {

    private static final long serialVersionUID = -1313040870L;

    public static final QBiUser biUser = new QBiUser("biUser");

    public final com.pf.healthybox.domain.config.QAuditingFields _super = new com.pf.healthybox.domain.config.QAuditingFields(this);

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath authDiv = createString("authDiv");

    public final StringPath authLevel = createString("authLevel");

    public final StringPath compName = createString("compName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath isDel = createString("isDel");

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath recoCode = createString("recoCode");

    public final StringPath serialCode = createString("serialCode");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPw = createString("userPw");

    public final StringPath zipcode = createString("zipcode");

    public QBiUser(String variable) {
        super(BiUser.class, forVariable(variable));
    }

    public QBiUser(Path<? extends BiUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBiUser(PathMetadata metadata) {
        super(BiUser.class, metadata);
    }

}

