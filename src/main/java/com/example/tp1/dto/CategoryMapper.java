package com.example.tp1.dto;

import com.example.tp1.entity.Category;

public class CategoryMapper {
    public static CategoryDto toDto(Category category){
        if (category == null) return null;

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null) return null;

        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
