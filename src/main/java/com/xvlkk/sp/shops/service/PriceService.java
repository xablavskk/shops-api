package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.config.CacheConfig;
import com.xvlkk.sp.shops.dto.PriceDTO;
import com.xvlkk.sp.shops.exceptions.BusinessException;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.PriceMapper;
import com.xvlkk.sp.shops.model.Price;
import com.xvlkk.sp.shops.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @CacheEvict(value = CacheConfig.PRICES, key = "#dto.sku.cdProductSku")
    public PriceDTO create(PriceDTO dto) {
        return priceMapper.toDto(priceRepository.save(priceMapper.toEntity(dto)));
    }

    @CacheEvict(value = CacheConfig.PRICES, allEntries = true)
    public PriceDTO update(Long id, PriceDTO dto) {
        Price existing = priceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price not found: " + id));
        priceMapper.partialUpdate(dto, existing);
        return priceMapper.toDto(priceRepository.save(existing));
    }

    @CacheEvict(value = CacheConfig.PRICES, allEntries = true)
    public void delete(Long id) {
        priceRepository.deleteById(id);
    }

    /**
     * Returns the effective price for a SKU at the current moment.
     * Promotional price takes precedence over regular price.
     */
    @Cacheable(value = CacheConfig.PRICES, key = "#skuId")
    public BigDecimal getEffectivePrice(Long skuId) {
        LocalDateTime now = LocalDateTime.now();

        return priceRepository.findActivePromotionalPrice(skuId, now)
                .map(Price::getNrAmount)
                .orElseGet(() -> priceRepository.findActiveRegularPrice(skuId, now)
                        .map(Price::getNrAmount)
                        .orElseThrow(() -> new BusinessException("No active price found for SKU: " + skuId)));
    }
}
