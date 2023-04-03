package com.zara.platform.service;

import com.zara.platform.entity.Price;
import com.zara.platform.entity.PriceDTO;
import com.zara.platform.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Cacheable(value = "priceCache", key = "{#productId, #brandId, #applicationDate}")
    public PriceDTO getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {

        List<Price> prices =
                priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        brandId, productId, applicationDate, applicationDate);

        if (prices.isEmpty()) {
            return null;
        }

        prices.sort(Comparator.comparingInt(Price::getPriority).reversed());

        for (Price price : prices) {
            if (applicationDate.isAfter(price.getStartDate()) || applicationDate.isEqual(price.getStartDate())
                    && (applicationDate.isBefore(price.getEndDate()) || applicationDate.isEqual(price.getEndDate()))) {
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

        return null;
    }
}
