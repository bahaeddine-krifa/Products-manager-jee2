package com.example.tp1.service;

import com.example.tp1.dto.CategoryDto;
import java.util.List;

public interface IServiceCategory {
    CategoryDto add(CategoryDto categoryDto);
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    CategoryDto update(Long id, CategoryDto categoryDto);
    void delete(Long id);
}