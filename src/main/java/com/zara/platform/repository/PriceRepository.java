package com.zara.platform.repository;

import com.zara.platform.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndPriority(
            Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate, Integer priority);

}
