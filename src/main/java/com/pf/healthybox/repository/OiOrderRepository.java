package com.pf.healthybox.repository;

import com.pf.healthybox.domain.baseInformation.QBiPoint;
import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.domain.orderInformation.QOiOrder;
import com.pf.healthybox.repository.querydsl.OiOrderRepositoryCustom;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface OiOrderRepository extends JpaRepository<OiOrder, Long>,
        OiOrderRepositoryCustom,
        QuerydslPredicateExecutor<OiOrder>,
        QuerydslBinderCustomizer<QOiOrder> {

    @Override
    default void customize(QuerydslBindings bindings, QOiOrder root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.orderNo);
        bindings.bind(root.orderNo).first(StringExpression::containsIgnoreCase);
    }
}