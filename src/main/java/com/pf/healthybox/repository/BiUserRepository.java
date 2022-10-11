package com.pf.healthybox.repository;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.domain.baseInformation.QBiUser;
import com.pf.healthybox.repository.querydsl.BiUserRepositoryCustom;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface BiUserRepository extends JpaRepository<BiUser, String>,
        BiUserRepositoryCustom,
        QuerydslPredicateExecutor<BiUser>,
        QuerydslBinderCustomizer<QBiUser> {

    void deleteByUserIdAndUserPw(String userId, String userPw);
    boolean existsByUserId(String userId);
    boolean existsByRecoCode(String recoCode);

    BiUser findByUserIdAndUserNameAndEmail(String userId, String name, String email);

    @Override
    default void customize(QuerydslBindings bindings, QBiUser root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.userId, root.userName, root.nickname);
        bindings.bind(root.userId).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.userName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.nickname).first(StringExpression::containsIgnoreCase);
    }
}
