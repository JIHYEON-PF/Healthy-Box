package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.adminConfig.Environment;
import com.pf.healthybox.domain.adminConfig.QEnvironment;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.HashMap;
import java.util.List;

public class EnvironmentRepositoryCustomImpl extends QuerydslRepositorySupport implements EnvironmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public EnvironmentRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Environment.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> fetchEnvData() {

        QEnvironment environment = QEnvironment.environment;

        return queryFactory
                .select(environment.apiName,
                        environment.apiKey)
                .from(environment)
                .orderBy(environment.idx.asc())
                .fetch();
    }
}
