package com.pf.healthybox.repository;

import com.pf.healthybox.domain.productInformation.PiProduct;
import com.pf.healthybox.domain.productInformation.PiProductPk;
import com.pf.healthybox.domain.productInformation.QPiProduct;
import com.pf.healthybox.repository.querydsl.PiProductRepositoryCustom;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface PiProductRepository extends JpaRepository<PiProduct, PiProductPk>,
        PiProductRepositoryCustom,
        QuerydslPredicateExecutor<PiProduct>,
        QuerydslBinderCustomizer<QPiProduct> {

    @Override
    default void customize(QuerydslBindings bindings, QPiProduct root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.piProductPk.productCode, root.piProductPk.sellerCode, root.productName,
                root.productGroup, root.productCategory, root.detail, root.isUsed);
        bindings.bind(root.piProductPk.productCode).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.piProductPk.sellerCode).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.productName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.productGroup).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.productCategory).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.detail).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.isUsed).first(StringExpression::containsIgnoreCase);
    };
}
