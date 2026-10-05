package com.example.tp1.service;

import com.example.tp1.dto.ProductDto;
import java.util.List;

public interface IServiceProduct {
    ProductDto add(ProductDto productDto);
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto update(Long id, ProductDto productDto);
    void delete(Long id);
    List<ProductDto> getProductByCategoryName(String categoryName);
}