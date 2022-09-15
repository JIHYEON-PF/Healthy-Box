package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.adminConfig.Environment;
import com.pf.healthybox.domain.adminConfig.QEnvironment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class EnvironmentRepositoryCustomImpl extends QuerydslRepositorySupport implements EnvironmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public EnvironmentRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Environment.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public String findLogisticsApiCode() {

        QEnvironment envir = QEnvironment.environment;

        return queryFactory
                .select(envir.logisticsApiCode)
                .from(envir)
                .fetchFirst();
    }


}
