package com.zara.platform.controller;

import com.zara.platform.entity.PriceDTO;
import com.zara.platform.exception.ResourceNotFoundException;
import com.zara.platform.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceControllerTest {

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRequestAt10AMOnDay14() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setPriceList(1);
        expectedPriceDTO.setPrice(35.50);

        when(priceService.getPrice(PRODUCT_ID, BRAND_ID, requestTime)).thenReturn(expectedPriceDTO);

        PriceDTO response = priceController.getPrice(PRODUCT_ID, BRAND_ID, requestTime);

        assertEquals(expectedPriceDTO.getPriceList(), response.getPriceList());
        assertEquals(expectedPriceDTO.getPrice(), response.getPrice());
    }

    @Test
    public void testRequestAt4PMOnDay14() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setPriceList(2);
        expectedPriceDTO.setPrice(25.45);

        when(priceService.getPrice(PRODUCT_ID, BRAND_ID, requestTime)).thenReturn(expectedPriceDTO);

        PriceDTO response = priceController.getPrice(PRODUCT_ID, BRAND_ID, requestTime);

        assertEquals(expectedPriceDTO.getPriceList(), response.getPriceList());
        assertEquals(expectedPriceDTO.getPrice(), response.getPrice());
    }

    @Test
    public void testRequestAt9PMOnDay14() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setPriceList(1);
        expectedPriceDTO.setPrice(35.50);

        when(priceService.getPrice(PRODUCT_ID, BRAND_ID, requestTime)).thenReturn(expectedPriceDTO);

        PriceDTO response = priceController.getPrice(PRODUCT_ID, BRAND_ID, requestTime);

        assertEquals(expectedPriceDTO.getPriceList(), response.getPriceList());
        assertEquals(expectedPriceDTO.getPrice(), response.getPrice());
    }

    @Test
    public void testRequestAt10AMOnDay15() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setPriceList(3);
        expectedPriceDTO.setPrice(30.50);

        when(priceService.getPrice(PRODUCT_ID, BRAND_ID, requestTime)).thenReturn(expectedPriceDTO);

        PriceDTO response = priceController.getPrice(PRODUCT_ID, BRAND_ID, requestTime);

        assertEquals(expectedPriceDTO.getPriceList(), response.getPriceList());
        assertEquals(expectedPriceDTO.getPrice(), response.getPrice());
    }

    @Test
    public void testRequestAt9PMOnDay16() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setPriceList(4);
        expectedPriceDTO.setPrice(38.95);

        when(priceService.getPrice(PRODUCT_ID, BRAND_ID, requestTime)).thenReturn(expectedPriceDTO);

        PriceDTO response = priceController.getPrice(PRODUCT_ID, BRAND_ID, requestTime);

        assertEquals(expectedPriceDTO.getPriceList(), response.getPriceList());
        assertEquals(expectedPriceDTO.getPrice(), response.getPrice());
    }

    @Test
    public void testInvalidProductIdOrBrandId() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long invalidProductId = 999L;
        Long invalidBrandId = 999L;

        when(priceService.getPrice(invalidProductId, BRAND_ID, requestTime))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        when(priceService.getPrice(PRODUCT_ID, invalidBrandId, requestTime))
                .thenThrow(new ResourceNotFoundException("Brand not found"));

        assertThrows(ResourceNotFoundException.class, () -> priceController.getPrice(invalidProductId, BRAND_ID, requestTime));
        assertThrows(ResourceNotFoundException.class, () -> priceController.getPrice(PRODUCT_ID, invalidBrandId, requestTime));
    }

    @Test
    public void testRequestOutsidePriceRange() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 6, 0, 0);
        Long productId = 123L;
        Long brandId = 456L;

        when(priceService.getPrice(productId, brandId, requestTime))
                .thenThrow(new ResourceNotFoundException("Price not found"));

        assertThrows(ResourceNotFoundException.class, () -> priceController.getPrice(productId, brandId, requestTime));
    }

    @Test
    public void testInvalidRequestParams() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        assertThrows(ResponseStatusException.class, () -> priceController.getPrice(null, BRAND_ID, requestTime));
        assertThrows(ResponseStatusException.class, () -> priceController.getPrice(PRODUCT_ID, null, requestTime));
        assertThrows(ResponseStatusException.class, () -> priceController.getPrice(PRODUCT_ID, BRAND_ID, null));
        assertThrows(ResponseStatusException.class, () -> priceController.getPrice(null, null, null));
    }

}