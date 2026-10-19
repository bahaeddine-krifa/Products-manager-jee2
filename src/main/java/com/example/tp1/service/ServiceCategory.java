package com.example.tp1.service;

import com.example.tp1.dto.CategoryDto;
import com.example.tp1.dto.CategoryMapper;
import com.example.tp1.entity.Category;
import com.example.tp1.repository.CategoryRepository;
import com.example.tp1.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceCategory implements IServiceCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = CategoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDto(savedCategory);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(c->CategoryMapper.toDto(c))
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        
        // Update fields from DTO to entity
        existingCategory.setName(categoryDto.getName());
        
        Category updatedCategory = categoryRepository.save(existingCategory);
        return CategoryMapper.toDto(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}