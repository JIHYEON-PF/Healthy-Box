package com.pf.healthybox.repository;

import com.pf.healthybox.domain.adminConfig.Environment;
import com.pf.healthybox.domain.adminConfig.QEnvironment;
import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.domain.orderInformation.QOiOrder;
import com.pf.healthybox.repository.querydsl.EnvironmentRepositoryCustom;
import com.pf.healthybox.repository.querydsl.OiOrderRepositoryCustom;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface EnvironmentRepository extends JpaRepository<Environment, Long>,
        EnvironmentRepositoryCustom,
        QuerydslPredicateExecutor<Environment>,
        QuerydslBinderCustomizer<QEnvironment> {

    @Override
    default void customize(QuerydslBindings bindings, QEnvironment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.logisticsApiCode);
        bindings.bind(root.logisticsApiCode).first(StringExpression::containsIgnoreCase);
    }
}
