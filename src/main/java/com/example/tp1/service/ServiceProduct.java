package com.example.tp1.service;

import com.example.tp1.dto.ProductDto;
import com.example.tp1.dto.ProductMapper;
import com.example.tp1.entity.Product;
import com.example.tp1.repository.ProductRepository;
import com.example.tp1.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceProduct implements IServiceProduct {

    private final ProductRepository productRepository;


    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = ProductMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
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
        return ProductMapper.toDto(product);
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
        return ProductMapper.toDto(updatedProduct);
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

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        // Generate a unique filename using the timestamp
        String newName = System.currentTimeMillis() + "_"
                + file.getOriginalFilename();

        // Define the upload directory
        String uploadDir = System.getProperty("user.home") + "/photos";
        Path uploadPath = Paths.get(uploadDir);

        // Create the directory if it does not exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Create the complete file path
        Path filePath = uploadPath.resolve(newName);

        // Save the file
        Files.copy(file.getInputStream(), filePath,
                StandardCopyOption.REPLACE_EXISTING);

        // Return the new filename
        return newName;
    }

    @Override
    public byte[] getImage(Long id) throws IOException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));

        String photoName = product.getPhoto();

        if (photoName == null || photoName.isBlank()) {
            throw new ResourceNotFoundException(
                    "No image associated with product: " + id);
        }

        Path imagePath = Paths.get(
                System.getProperty("user.home"), "photos", photoName);

        return Files.readAllBytes(imagePath);
    }

}