package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.baseInformation.QBiPoint;
import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.domain.orderInformation.QOiOrder;
import com.pf.healthybox.domain.productInformation.QPiProduct;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public class OiOrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OiOrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OiOrderRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(OiOrder.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> findOrderList(String userId) {

        QOiOrder oiOrder = QOiOrder.oiOrder;
        QPiProduct piProduct = QPiProduct.piProduct;

        return queryFactory
                .select(oiOrder.orderNo.as("orderNo"),
                        oiOrder.createdAt.as("createdAt"),
                        oiOrder.status.as("status"),
                        piProduct.productName.as("productName"),
                        oiOrder.count().as("productCnt"),
                        oiOrder.amount.sum().as("amount"))
                .from(oiOrder)
                .leftJoin(piProduct).on(oiOrder.productCode.eq(piProduct.piProductPk.productCode))
                .where(oiOrder.userId.eq(userId))
                .groupBy(oiOrder.orderNo, oiOrder.createdAt, piProduct.productName, oiOrder.status)
                .orderBy(oiOrder.createdAt.desc())
                .fetch();

    }

}
