package com.zara.platform.controller;

import com.zara.platform.entity.PriceDTO;
import com.zara.platform.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public PriceDTO getPrice(@RequestParam Long productId,
                             @RequestParam Long brandId,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        PriceDTO prices = priceService.getPrice(productId, brandId, applicationDate);

        if (prices == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró un precio para el producto y la fecha especificados.");
        }

        return prices;
    }
}
