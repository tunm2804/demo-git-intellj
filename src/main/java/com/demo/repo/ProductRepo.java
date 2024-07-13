package com.demo.repo;

import com.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1 AND p.category.name LIKE ?2")
    Page<Product> findByKeyword(String keyword,String category, Pageable pageable);
//
//    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1 ")
//    List<Product> findByKeywordd(String keyword);
//
//    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
//    List<Product> findByPrice(double minPrice, double maxPrice);
//
//    @Query("SELECT p FROM Product p WHERE p.category.name LIKE ?1")
//    List<Product> findBycategoryId(String category);
//
//    @Query("FROM Product WHERE (name LIKE %:keyword% OR category.name LIKE %:keyword%)")
//    List<Product> search(@Param("keyword") String keyword);
@Query("SELECT pr FROM Product pr WHERE pr.name LIKE ?1 AND pr.category.id LIKE ?2 AND pr.price BETWEEN ?3 AND ?4")
Page<Product> search(String keyword, String categoryId, int minPrice, int maxPrice, Pageable pageable);
}
