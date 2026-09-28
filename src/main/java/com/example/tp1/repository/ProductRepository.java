package com.example.tp1.repository;

import com.example.tp1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //Java Persistence Query Language

    public List<Product> findByNameContainsIgnoreCase(String mc);

    //derived query
    @Query("select p from Product p where p.name like %:x%")
    public List<Product> searchwithkeyword(@Param("x") String mc);

    public List<Product> findByCategoryName(String name);

    public List<Product> findTop3ByOrderByPriceDesc();
}
