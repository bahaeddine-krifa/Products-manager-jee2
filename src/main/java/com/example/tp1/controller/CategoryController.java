package com.example.tp1.controller;

import com.example.tp1.dto.CategoryDto;
import com.example.tp1.entity.Category;
import com.example.tp1.service.IServiceCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final IServiceCategory serviceCategory;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        return ResponseEntity.ok(serviceCategory.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id)
    {
        return ResponseEntity.ok(serviceCategory.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> addCategory(
            @Valid @RequestBody CategoryDto categoryDto
    )
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                serviceCategory.add(categoryDto)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        serviceCategory.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CategoryDto categoryDto,
            @PathVariable Long id
    )
    {
        return ResponseEntity.ok(serviceCategory.update(id,categoryDto));
    }
}