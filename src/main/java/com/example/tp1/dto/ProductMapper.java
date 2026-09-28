package com.example.tp1.dto;

import com.example.tp1.entity.Category;
import com.example.tp1.entity.Product;

public class ProductMapper {
    public static ProductDto toDto(Product product){
        if (product == null) return null;

        ProductDto productDto =
                ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .photo(product.getPhoto())
                        .build();

                        if(product.getCategory() != null){
                            productDto.setIdCategory(product.getCategory().getId());
                            productDto.setNameCategory(product.getCategory().getName());
                        }

        return productDto;
    }
    public static Product toEntity(ProductDto productDto){
        if (productDto == null) return null;

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .photo(productDto.getPhoto())
                .category(Category.builder().id(productDto.getIdCategory()).name(productDto.getNameCategory()).build())
                .build();
    }
}
