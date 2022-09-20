package com.pf.healthybox.repository;

import com.pf.healthybox.domain.orderInformation.OiBasket;
import com.pf.healthybox.domain.orderInformation.QOiBasket;
import com.pf.healthybox.repository.querydsl.OiBasketRepositoryCustom;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface OiBasketRepository extends JpaRepository<OiBasket, Long>,
        OiBasketRepositoryCustom,
        QuerydslPredicateExecutor<OiBasket>,
        QuerydslBinderCustomizer<QOiBasket> {

    @Override
    default void customize(QuerydslBindings bindings, QOiBasket root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.productCode, root.sellerCode, root.userId);
        bindings.bind(root.productCode).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.sellerCode).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.userId).first(StringExpression::containsIgnoreCase);
    };
}
