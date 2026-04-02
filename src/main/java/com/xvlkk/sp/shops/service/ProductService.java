package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.config.CacheConfig;
import com.xvlkk.sp.shops.dto.ProductDTO;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.ProductMapper;
import com.xvlkk.sp.shops.model.Product;
import com.xvlkk.sp.shops.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findByStActiveTrue(pageable).map(productMapper::toDto);
    }

    public Page<ProductDTO> findByCategory(Long cdCategory, Pageable pageable) {
        return productRepository.findByCategoryCdCategory(cdCategory, pageable).map(productMapper::toDto);
    }

    public Page<ProductDTO> search(String name, Pageable pageable) {
        return productRepository.findByStActiveTrueAndNmProductContainingIgnoreCase(name, pageable)
                .map(productMapper::toDto);
    }

    @Cacheable(value = CacheConfig.PRODUCTS, key = "#id")
    public ProductDTO findById(Long id) {
        return productMapper.toDto(findOrThrow(id));
    }

    @CacheEvict(value = CacheConfig.PRODUCTS, allEntries = true)
    public ProductDTO create(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        product.setStActive(true);
        return productMapper.toDto(productRepository.save(product));
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConfig.PRODUCTS, key = "#id"),
            @CacheEvict(value = CacheConfig.PRODUCTS, allEntries = true)
    })
    public ProductDTO update(Long id, ProductDTO dto) {
        Product existing = findOrThrow(id);
        productMapper.partialUpdate(dto, existing);
        return productMapper.toDto(productRepository.save(existing));
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConfig.PRODUCTS, key = "#id"),
            @CacheEvict(value = CacheConfig.PRODUCTS, allEntries = true)
    })
    public void delete(Long id) {
        Product product = findOrThrow(id);
        product.setStActive(false);
        productRepository.save(product); // soft delete
    }

    private Product findOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }
}
