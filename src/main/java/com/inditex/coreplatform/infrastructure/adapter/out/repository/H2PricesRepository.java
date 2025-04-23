package com.inditex.coreplatform.infrastructure.adapter.out.repository;

import com.inditex.coreplatform.domain.port.PricesRepository;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface H2PricesRepository extends JpaRepository<PricesEntity, Long>, PricesRepository {

    @Override
    @Query("SELECT p FROM PricesEntity p WHERE p.startDate = :startDate AND p.productId = :productId AND p.brandId = :brandId")
    PricesEntity findPricesByStartDateAndProductIdAndBrandId(@Param("startDate") String startDate,
                                                             @Param("productId") Long productId,
                                                             @Param("brandId") Long brandId);
}