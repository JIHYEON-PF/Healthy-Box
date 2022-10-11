package com.pf.healthybox.repository.querydsl;

public interface BiUserRepositoryCustom {

    void UpdateUserPw(String userId, String userPw);

    String findUserIdByUserNameAndEmail(String userName, String email);
}
