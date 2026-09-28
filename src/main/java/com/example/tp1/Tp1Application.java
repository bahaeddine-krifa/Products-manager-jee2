package com.example.tp1;

import com.example.tp1.entity.Category;
import com.example.tp1.entity.Product;
import com.example.tp1.repository.CategoryRepository;
import com.example.tp1.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp1Application {


    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository){
        return args -> {

            Category c1 = new Category(null,"informatique",null);
            Category c2  = Category.builder().name("elecronic").build();
            categoryRepository.save(c1);
            categoryRepository.save(c2);

            productRepository.save(
                    Product.builder()
                            .price(50)
                            .name("keyboard")
                            .quantity(10)
                            .category(c1)
                            .build()
            );

            productRepository.save(
                    Product.builder()
                            .price(5000)
                            .name("smartphone")
                            .quantity(15)
                            .category(c2)
                            .build()
            );
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Tp1Application.class, args);
    }

}
