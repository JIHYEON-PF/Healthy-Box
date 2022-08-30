package com.pf.healthybox.repository.querydsl;

public interface BiPointRepositoryCustom {

    Integer findByUserIdAndAndIsExpiredAndDateDiff(String userId);

    Integer findByUserIdAndIsExpired(String userId);
}
