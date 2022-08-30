package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.baseInformation.BiPoint;
import com.pf.healthybox.domain.baseInformation.QBiPoint;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberOperation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.Date;

public class BiPointRepositoryCustomImpl  extends QuerydslRepositorySupport implements BiPointRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BiPointRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) { super(BiPoint.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Integer findByUserIdAndAndIsExpiredAndDateDiff(String userId) {
        QBiPoint qBiPoint = QBiPoint.biPoint;

        return from (qBiPoint)
                .select(qBiPoint.occurPoint.sum())
                .where(qBiPoint.isExpired.eq("N")
                        .and(qBiPoint.userId.eq(userId))
                        .and(Expressions.dateTemplate(LocalDateTime.class, "ADDDATE({0},{1})", Expressions.currentDate(), Expressions.asDate(5)).after(qBiPoint.expireDate)))
                .fetchFirst();

//        NumberOperation<Integer> dayDiff = Expressions.numberOperation(Integer.class,
//                Ops.DateTimeOps.DIFF_DAYS,
//                qBiPoint.expireDate,
//                Expressions.currentDate());
//
//        return jpaQueryFactory
//                .select(qBiPoint.occurPoint.sum(), Expressions.date)
//                .from(qBiPoint)
//                .where(dayDiff.lt(5).and(qBiPoint.isExpired.eq("N")).and(qBiPoint.userId.eq(userId)))
//                .fetchFirst();
    }

    @Override
    public Integer findByUserIdAndIsExpired(String userId) {
        QBiPoint qBiPoint = QBiPoint.biPoint;

        return from(qBiPoint)
                .select(qBiPoint.occurPoint.sum())
                .where(qBiPoint.isExpired.eq("N").and(qBiPoint.userId.eq(userId)))
                .fetchFirst();
    }
}
