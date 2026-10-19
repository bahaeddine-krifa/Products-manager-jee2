package com.example.tp1.controller;

import com.example.tp1.dto.ProductDto;
import com.example.tp1.entity.Product;
import com.example.tp1.service.IServiceProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final IServiceProduct serviceProduct;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts()
    {
        return ResponseEntity.ok(serviceProduct.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(serviceProduct.getById(id));
    }

    /*@PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(
            @Valid @RequestBody ProductDto productDto
    )
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                serviceProduct.add(productDto)
        );
    }*/
    @PostMapping(
            value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ProductDto> addProductWithImage(
            @RequestPart("product") @Valid ProductDto dto,
            @RequestPart(value = "file", required = false)
            MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            String photoName = serviceProduct.uploadFile(file);
            dto.setPhoto(photoName);
        }

        ProductDto saved = serviceProduct.add(dto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/image/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getImage(
            @PathVariable Long id) throws IOException {

        byte[] image = serviceProduct.getImage(id);

        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        serviceProduct.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @Valid @RequestBody ProductDto productDto,
            @PathVariable Long id
    )
    {
        return ResponseEntity.ok(serviceProduct.update(id,productDto));
    }
}
