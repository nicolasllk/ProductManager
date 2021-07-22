package com.product.productManager.domain.repository;

import com.product.productManager.domain.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    List<Product> findByName(String name);
    Optional<Product> findByProductID(String productID);
    long deleteByProductID(String productID);
}
