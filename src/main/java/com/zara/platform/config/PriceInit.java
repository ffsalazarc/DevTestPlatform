package com.zara.platform.config;

import com.zara.platform.entity.Price;
import com.zara.platform.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class PriceInit implements CommandLineRunner {

    private final PriceRepository priceRepository;

    public PriceInit(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Price price1 = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(35.50)
                .currency("EUR")
                .build();

        Price price2 = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(2)
                .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
                .price(25.45)
                .currency("EUR")
                .build();

        Price price3 = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T11:00:00"))
                .price(30.50)
                .currency("EUR")
                .build();

        Price price4 = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(38.95)
                .currency("EUR")
                .build();

        priceRepository.saveAll(Arrays.asList(price1, price2, price3, price4));
    }
}
