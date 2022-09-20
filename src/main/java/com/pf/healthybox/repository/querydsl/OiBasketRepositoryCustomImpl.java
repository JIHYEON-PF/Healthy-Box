package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.orderInformation.OiBasket;
import com.pf.healthybox.domain.orderInformation.QOiBasket;
import com.pf.healthybox.domain.productInformation.QPiProduct;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OiBasketRepositoryCustomImpl extends QuerydslRepositorySupport implements OiBasketRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OiBasketRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(OiBasket.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> showBasketList(String userId) {

        QOiBasket oiBasket = QOiBasket.oiBasket;
        QPiProduct piProduct = QPiProduct.piProduct;

        return queryFactory
                .select(oiBasket.sellerCode,
                        oiBasket.productCode,
                        piProduct.productName,
                        oiBasket.qty,
                        piProduct.price,
                        oiBasket.qty.multiply(piProduct.price).as("amount"))
                .from(oiBasket)
                .leftJoin(piProduct).on(oiBasket.productCode.eq(piProduct.piProductPk.productCode))
                .where(oiBasket.userId.eq(userId))
                .orderBy(oiBasket.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Tuple> showBasketPrice(String userId) {

        QOiBasket oiBasket = QOiBasket.oiBasket;
        QPiProduct piProduct = QPiProduct.piProduct;

        return queryFactory
                .select(oiBasket.qty.multiply(piProduct.price).sum().as("amount"),
                        new CaseBuilder()
                                .when((oiBasket.qty.multiply(piProduct.price).sum()).goe(50000))
                                .then(0)
                                .otherwise(3000)
                                .as("delivery_cost"))
                .from(oiBasket)
                .leftJoin(piProduct).on(oiBasket.productCode.eq(piProduct.piProductPk.productCode))
                .where(oiBasket.userId.eq(userId))
                .groupBy(oiBasket.sellerCode)
                .fetch();

    }
}
