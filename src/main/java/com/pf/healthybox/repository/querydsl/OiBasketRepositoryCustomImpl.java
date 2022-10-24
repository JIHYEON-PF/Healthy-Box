package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.orderInformation.OiBasket;
import com.pf.healthybox.domain.orderInformation.QOiBasket;
import com.pf.healthybox.domain.orderInformation.QOiSubscribeBasket;
import com.pf.healthybox.domain.productInformation.QPiProduct;
import com.pf.healthybox.domain.productInformation.QPiSubscribeProducts;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
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

    @Override
    public void updateBasketQty(String userId, String productCode, Integer qty) {

        QOiBasket oiBasket = QOiBasket.oiBasket;

        queryFactory
            .update(oiBasket)
            .set(oiBasket.qty, qty)
            .where(oiBasket.userId.eq(userId).and(oiBasket.productCode.eq(productCode)))
            .execute();
    }

    @Override
    public void deleteBasketItems(String userId, String[] productCode) {

        QOiBasket oiBasket = QOiBasket.oiBasket;

        queryFactory
            .delete(oiBasket)
            .where(oiBasket.userId.eq(userId)
                    .and(oiBasket.productCode.in(productCode))
            )
            .execute();


    }

    @Override
    public List<Tuple> showSubscribeBasketList(String userId) {

        QOiSubscribeBasket oiSubscribeBasket = QOiSubscribeBasket.oiSubscribeBasket;
        QPiSubscribeProducts subscribeProducts = QPiSubscribeProducts.piSubscribeProducts;
        QPiProduct piProduct = QPiProduct.piProduct;

        return queryFactory
                .select(oiSubscribeBasket.basketNo,
                        oiSubscribeBasket.createdAt,
                        oiSubscribeBasket.subscribeCode,
                        subscribeProducts.subscribeName,
                        (piProduct.price.multiply(oiSubscribeBasket.qty).sum()).as("amount"),
                        oiSubscribeBasket.deliveryDate.min().as("startDate"),
                        oiSubscribeBasket.deliveryDate.max().as("endDate"),
                        oiSubscribeBasket.sellerCode)
                .from(oiSubscribeBasket)
                .leftJoin(subscribeProducts).on(oiSubscribeBasket.subscribeCode.eq(subscribeProducts.subscribeCode)
                        .and(oiSubscribeBasket.productCode.eq(subscribeProducts.productCode)))
                .leftJoin(piProduct).on(oiSubscribeBasket.productCode.eq(piProduct.piProductPk.productCode)
                        .and(subscribeProducts.sellerCode.eq(piProduct.piProductPk.sellerCode)))
                .where(oiSubscribeBasket.userId.eq(userId))
                .groupBy(oiSubscribeBasket.basketNo)
                .orderBy(oiSubscribeBasket.basketNo.desc())
                .fetch();
    }
}
