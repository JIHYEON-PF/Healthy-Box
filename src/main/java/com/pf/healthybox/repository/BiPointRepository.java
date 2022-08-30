package com.pf.healthybox.repository;


import com.pf.healthybox.domain.baseInformation.BiPoint;
import com.pf.healthybox.domain.baseInformation.QBiPoint;
import com.pf.healthybox.domain.productInformation.PiDivProduct;
import com.pf.healthybox.domain.productInformation.QPiDivProduct;
import com.pf.healthybox.repository.querydsl.BiPointRepositoryCustom;
import com.pf.healthybox.repository.querydsl.PiDivProductRepositoryCustom;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface BiPointRepository extends JpaRepository<BiPoint, Long>,
        BiPointRepositoryCustom,
        QuerydslPredicateExecutor<BiPoint>,
        QuerydslBinderCustomizer<QBiPoint> {

    List<BiPoint> findByUserId(String userId);

    @Override
    default void customize(QuerydslBindings bindings, QBiPoint root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
    };


}
