package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.config.CacheConfig;
import com.xvlkk.sp.shops.dto.CategoryDTO;
import com.xvlkk.sp.shops.exceptions.BusinessException;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.CategoryMapper;
import com.xvlkk.sp.shops.model.Category;
import com.xvlkk.sp.shops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Cacheable(CacheConfig.CATEGORIES)
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @Cacheable(value = CacheConfig.CATEGORIES, key = "#id")
    public CategoryDTO findById(Long id) {
        return categoryMapper.toDto(findOrThrow(id));
    }

    @CacheEvict(value = CacheConfig.CATEGORIES, allEntries = true)
    public CategoryDTO create(CategoryDTO dto) {
        if (categoryRepository.existsByNmSlug(dto.getNmSlug())) {
            throw new BusinessException("Slug already in use: " + dto.getNmSlug());
        }
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(dto)));
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConfig.CATEGORIES, key = "#id"),
            @CacheEvict(value = CacheConfig.CATEGORIES, allEntries = true)
    })
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category existing = findOrThrow(id);
        categoryMapper.partialUpdate(dto, existing);
        return categoryMapper.toDto(categoryRepository.save(existing));
    }

    @Caching(evict = {
            @CacheEvict(value = CacheConfig.CATEGORIES, key = "#id"),
            @CacheEvict(value = CacheConfig.CATEGORIES, allEntries = true)
    })
    public void delete(Long id) {
        categoryRepository.delete(findOrThrow(id));
    }

    private Category findOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    }
}
