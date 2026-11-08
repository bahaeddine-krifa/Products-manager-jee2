package com.example.tp1;

import com.example.tp1.entity.AppRole;
import com.example.tp1.entity.AppUser;
import com.example.tp1.entity.Category;
import com.example.tp1.entity.Product;
import com.example.tp1.repository.AppRoleRepository;
import com.example.tp1.repository.AppUserRepository;
import com.example.tp1.repository.CategoryRepository;
import com.example.tp1.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Tp1Application {

    /*@Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository){
        return args -> {
            if (categoryRepository.count() == 0) {
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
            }
        };
    }*/

    @Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository , PasswordEncoder passwordEncoder){
        return args -> {
            if (appRoleRepository.count() == 0) {
                AppRole roleUser = AppRole.builder().nom("USER").build();
                AppRole roleAdmin = AppRole.builder().nom("ADMIN").build();
                appRoleRepository.save(roleUser);
                appRoleRepository.save(roleAdmin);
            }

            AppRole roleUser = appRoleRepository.findById("USER").get();
            AppRole roleAdmin = appRoleRepository.findById("ADMIN").get();

            if (appUserRepository.count() ==0){
                appUserRepository.save(
                        AppUser.builder()
                                .id(UUID.randomUUID().toString())
                                .userName("user")
                                .password(passwordEncoder.encode("1234"))
                                .roles(List.of(roleUser))
                                .build()
                );

                appUserRepository.save(
                        AppUser.builder()
                                .id(UUID.randomUUID().toString())
                                .userName("admin")
                                .password(passwordEncoder.encode("1234"))
                                .roles(List.of(roleAdmin , roleUser))
                                .build()
                );
            }

            System.out.println("Users and roles have been initialized.");
            System.out.println("Total users: " + appUserRepository.count());
            System.out.println("the first user is : " + appUserRepository.findByUserName("user").getUserName());

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Tp1Application.class, args);
    }

}
