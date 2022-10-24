package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.baseInformation.QBiPoint;
import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.domain.orderInformation.QOiDeliver;
import com.pf.healthybox.domain.orderInformation.QOiOrder;
import com.pf.healthybox.domain.productInformation.QPiProduct;
import com.pf.healthybox.domain.productInformation.QPiSubscribeProducts;
import com.pf.healthybox.dto.orderInformationDto.OiOrderDetailDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
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
    public List<Tuple> findOrderList(String userId, String isSubscribe) {

        QOiOrder oiOrder = QOiOrder.oiOrder;
        QPiProduct piProduct = QPiProduct.piProduct;

        return queryFactory
                .select(oiOrder.orderNo.as("orderNo"),
                        oiOrder.createdAt.as("createdAt"),
                        oiOrder.status.as("status"),
                        piProduct.productName.as("productName"),
                        oiOrder.count().as("productCnt"),
                        (oiOrder.amount.subtract(oiOrder.dcCost).add(oiOrder.deliveryCost)).sum().as("amount"),
                        oiOrder.isSubscribe.as("isSubscribe"),
                        oiOrder.subscribeCode.as("subscribeCode"),
                        oiOrder.deliveryDate.as("deliveryDate"))
                .from(oiOrder)
                .leftJoin(piProduct).on(oiOrder.productCode.eq(piProduct.piProductPk.productCode))
                .where(oiOrder.userId.eq(userId).and(oiOrder.isSubscribe.eq(isSubscribe)))
                .groupBy(oiOrder.orderNo, oiOrder.createdAt, oiOrder.status)
                .orderBy(oiOrder.createdAt.desc())
                .fetch();

    }

    @Override
    public List<Tuple> findSubscribeOrderList(String userId, String isSubscribe) {

        QOiOrder oiOrder = QOiOrder.oiOrder;
        QPiSubscribeProducts subscribeProducts = QPiSubscribeProducts.piSubscribeProducts;

        return queryFactory
                .select(oiOrder.orderNo,
                        oiOrder.createdAt,
                        oiOrder.amount.sum().as("amount"),
                        oiOrder.status,
                        subscribeProducts.subscribeName,
                        oiOrder.deliveryDate.min().as("startDate"),
                        oiOrder.deliveryDate.max().as("endDate"))
                .from(oiOrder)
                .leftJoin(subscribeProducts).on(oiOrder.subscribeCode.eq(subscribeProducts.subscribeCode).and(oiOrder.productCode.eq(subscribeProducts.productCode)))
                .where(oiOrder.userId.eq(userId).and(oiOrder.isSubscribe.eq(isSubscribe)))
                .groupBy(oiOrder.orderNo)
                .orderBy(oiOrder.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Tuple> findOrderDetail(String userId, String orderNo, String isSubscribe) {

        QOiOrder oiOrder = QOiOrder.oiOrder;
        QOiOrder subOiOrder = new QOiOrder("subOiOrder");
        QPiProduct piProduct = QPiProduct.piProduct;
        QOiDeliver oiDeliver = QOiDeliver.oiDeliver;

        JPAQuery<Tuple> query = queryFactory
                .select(oiOrder.createdAt,
                        oiOrder.orderNo,
                        oiOrder.orderIdx,
                        oiOrder.status,
                        oiOrder.productCode,
                        piProduct.productName,
                        oiOrder.unitCost,
                        oiOrder.qty,
                        oiOrder.dcCost,
                        oiOrder.amount.subtract(oiOrder.dcCost).as("amount"),
                        oiOrder.deliveryComp,
                        oiOrder.deliveryCode,
                        oiDeliver.deliveryName,
                        oiDeliver.zipcode,
                        oiDeliver.address1,
                        oiDeliver.address2,
                        oiOrder.payMethod,
                        oiOrder.deliveryCost,
                        ExpressionUtils.as(
                                JPAExpressions.select(subOiOrder.dcCost.sum())
                                        .from(subOiOrder)
                                        .where(subOiOrder.orderNo.eq(oiOrder.orderNo)),
                                "totalDcCost"
                            ),
                        ExpressionUtils.as(
                                JPAExpressions.select(subOiOrder.amount.sum())
                                        .from(subOiOrder)
                                        .where(subOiOrder.orderNo.eq(oiOrder.orderNo)),
                                "totalAmount"
                            ),
                        oiOrder.cardName,
                        oiOrder.quota,
                        oiOrder.deliveryDate
                        )
                .from(oiOrder)
                .leftJoin(piProduct).on(oiOrder.productCode.eq(piProduct.piProductPk.productCode))
                .leftJoin(oiDeliver).on(oiOrder.deliverIdx.eq(oiDeliver.idx))
                .where(oiOrder.userId.eq(userId).and(oiOrder.orderNo.eq(orderNo)))
                .groupBy(oiOrder.createdAt, oiOrder.orderNo, oiOrder.orderIdx, oiOrder.status, oiOrder.productCode, piProduct.productName, oiOrder.unitCost, oiOrder.qty, oiOrder.dcCost,
                        oiOrder.amount, oiOrder.deliveryComp, oiOrder.deliveryCode, oiDeliver.deliveryName,oiDeliver.zipcode, oiDeliver.address1, oiDeliver.address2, oiOrder.payMethod, oiOrder.deliveryCost, oiOrder.orderIdx)
                ;

        if (isSubscribe.equals("N")) {
            query.orderBy(oiOrder.orderNo.asc());
        } else if (isSubscribe.equals("Y")) {
            query.orderBy(oiOrder.deliveryDate.asc());
        }

        return query.fetch();
    }

    @Override
    public void deleteOrderDetail(String userId, String orderNo) {

        QOiOrder qOiOrder = QOiOrder.oiOrder;

        queryFactory
                .delete(qOiOrder)
                .where(qOiOrder.userId.eq(userId)
                        .and(qOiOrder.orderNo.eq(orderNo)))
                .execute();
    }

    @Override
    public String findSellerCodeByOrderNo(String orderNo) {

        QOiOrder qOiOrder = QOiOrder.oiOrder;

        return queryFactory
                .select(qOiOrder.sellerCode)
                .from(qOiOrder)
                .where(qOiOrder.orderNo.eq(orderNo))
                .fetchFirst();
    }

    @Override
    public void updateStatusCode(String orderNo, String sellerCode, String statusCode) {
        QOiOrder oiOrder = QOiOrder.oiOrder;

        queryFactory
                .update(oiOrder)
                .set(oiOrder.status, Status.valueOf(Status.class, statusCode))
                .where(oiOrder.orderNo.eq(orderNo).and(oiOrder.sellerCode.eq(sellerCode)))
                .execute();
    }

}
