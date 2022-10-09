package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.productInformation.PiProduct;
import com.pf.healthybox.domain.productInformation.QPiProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PiProductRepositoryCustomImpl extends QuerydslRepositorySupport implements PiProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PiProductRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(PiProduct.class);
        this.queryFactory = queryFactory;
    };

    @Override
    public PiProduct findProducts(String productCode,String sellerCode) {

        QPiProduct piProduct = QPiProduct.piProduct;

        return queryFactory
                .selectFrom(piProduct)
                .where(piProduct.piProductPk.productCode.eq(productCode))
                .fetchOne();
    }
}
