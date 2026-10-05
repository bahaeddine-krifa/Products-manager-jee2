package com.example.tp1.service;

import com.example.tp1.dto.ProductDto;
import com.example.tp1.dto.ProductMapper;
import com.example.tp1.entity.Product;
import com.example.tp1.repository.ProductRepository;
import com.example.tp1.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduct implements IServiceProduct {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(p->ProductMapper.toDto(p))
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        // Update fields from DTO to entity
        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setPhoto(productDto.getPhoto());
        
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getProductByCategoryName(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return products.stream()
                .map(p->ProductMapper.toDto(p))
                .toList();
    }
}