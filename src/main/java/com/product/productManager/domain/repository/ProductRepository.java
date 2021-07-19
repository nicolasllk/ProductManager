package com.product.productManager.domain.repository;

import com.product.productManager.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    List<Product> findByName(String name);
}
