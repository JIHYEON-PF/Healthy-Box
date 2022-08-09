package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.productInformation.PiDivProduct;
import com.pf.healthybox.domain.productInformation.QPiDivProduct;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PiDivProductRepositoryCustomImpl extends QuerydslRepositorySupport implements PiDivProductRepositoryCustom {

    public PiDivProductRepositoryCustomImpl() { super(PiDivProduct.class);}

    @Override
    public List<String> findAllDistinctProductGroup() {

        QPiDivProduct piDivProduct = QPiDivProduct.piDivProduct;

        return from(piDivProduct)
                .distinct()
                .select(piDivProduct.groupName)
                .where(piDivProduct.isUsed.eq("Y"))
                .orderBy(piDivProduct.piDivProductPk.productGroup.asc())
                .fetch();
    }

    @Override
    public List<Tuple> findAllProductGroup() {

        QPiDivProduct piDivProduct = QPiDivProduct.piDivProduct;

         return from(piDivProduct)
            .select(piDivProduct.groupName, piDivProduct.categoryName)
            .where(piDivProduct.isUsed.eq("Y"))
            .orderBy(piDivProduct.piDivProductPk.productGroup.asc(), piDivProduct.piDivProductPk.productCategory.asc())
            .fetch();


    }
}
