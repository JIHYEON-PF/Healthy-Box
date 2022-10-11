package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.domain.baseInformation.QBiUser;
import com.pf.healthybox.domain.orderInformation.QOiBasket;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BiUserRepositoryCustomImpl extends QuerydslRepositorySupport implements BiUserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BiUserRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(BiUser.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public void UpdateUserPw(String userId, String userPw) {
        QBiUser biUser = QBiUser.biUser;

        queryFactory
                .update(biUser)
                .set(biUser.userPw, userPw)
                .where(biUser.userId.eq(userId))
                .execute();
    }

    @Override
    public String findUserIdByUserNameAndEmail(String userName, String email) {

        QBiUser biUser = QBiUser.biUser;

        return queryFactory
                .select(biUser.userId)
                .from(biUser)
                .where(biUser.userName.eq(userName).and(biUser.email.eq(email)))
                .fetchOne();
    }
}
