package com.pf.healthybox.repository;

import com.pf.healthybox.domain.productInformation.PiDivProduct;
import com.pf.healthybox.domain.productInformation.PiDivProductPk;
import com.pf.healthybox.domain.productInformation.QPiDivProduct;
import com.pf.healthybox.repository.querydsl.PiDivProductRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;
import java.util.Map;


public interface PiDivProductRepository extends
        JpaRepository<PiDivProduct, PiDivProductPk>,
        PiDivProductRepositoryCustom,
        QuerydslPredicateExecutor<PiDivProduct>,
        QuerydslBinderCustomizer<QPiDivProduct>
        {

        @Override
        default void customize(QuerydslBindings bindings, QPiDivProduct root) {
            bindings.excludeUnlistedProperties(true);
            bindings.including(root.piDivProductPk.productGroup, root.piDivProductPk.productCategory, root.categoryName, root.groupName, root.isUsed);
            bindings.bind(root.piDivProductPk.productGroup).first(StringExpression::containsIgnoreCase);
            bindings.bind(root.piDivProductPk.productCategory).first(StringExpression::containsIgnoreCase);
            bindings.bind(root.groupName).first(StringExpression::containsIgnoreCase);
            bindings.bind(root.categoryName).first(StringExpression::containsIgnoreCase);
            bindings.bind(root.isUsed).first(StringExpression::containsIgnoreCase);
        };


}
