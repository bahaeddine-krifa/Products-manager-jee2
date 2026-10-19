package com.example.tp1.service;

import com.example.tp1.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceProduct {
    ProductDto add(ProductDto productDto);
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto update(Long id, ProductDto productDto);
    void delete(Long id);
    List<ProductDto> getProductByCategoryName(String categoryName);
    String uploadFile(MultipartFile file) throws IOException;
    byte[] getImage(Long id) throws IOException;
}