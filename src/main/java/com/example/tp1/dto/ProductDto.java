package com.example.tp1.dto;

import com.example.tp1.entity.Category;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank(message = "Product Name is obligatory")
    private String name;
    @Min(value = 0, message = "Positif price")
    private double price;
    @Min(value = 0, message = "Positif quantity")
    private int quantity;
    private String photo;
    private Long idCategory;
    private String nameCategory;
}
