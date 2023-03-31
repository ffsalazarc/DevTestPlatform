package com.zara.platform.service;

import com.zara.platform.entity.Price;
import com.zara.platform.entity.PriceDTO;
import com.zara.platform.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Cacheable(value = "priceCache", key = "{#productId, #brandId, #applicationDate}")
    public PriceDTO getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {

        List<Price> prices =
                priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndPriority(
                        brandId, productId, applicationDate, applicationDate, 1);

        if (prices.isEmpty()) {
            return null;
        }

        Price price = prices.get(0);

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBrandId(price.getBrandId());
        priceDTO.setProductId(price.getProductId());
        priceDTO.setPriceList(price.getPriceList());
        priceDTO.setStartDate(price.getStartDate());
        priceDTO.setEndDate(price.getEndDate());
        priceDTO.setPrice(price.getPrice());
        priceDTO.setCurrency(price.getCurrency());

        return priceDTO;
    }
}
