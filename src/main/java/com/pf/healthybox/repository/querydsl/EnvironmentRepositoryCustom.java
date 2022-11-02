package com.pf.healthybox.repository.querydsl;

import com.querydsl.core.Tuple;

import java.util.HashMap;
import java.util.List;

public interface EnvironmentRepositoryCustom {

    List<Tuple> fetchEnvData();
}
