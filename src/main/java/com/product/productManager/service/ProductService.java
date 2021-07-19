package com.product.productManager.service;

import com.product.productManager.domain.model.Product;
import com.product.productManager.domain.repository.ProductRepository;
import com.product.productManager.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(int id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Product id: " + id +" not found."));
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product newProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(int id, Product newProduct) {
        Product updatableProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Unable to update product id " +id+ ". Product not found."));

        updatableProduct.setName(newProduct.getName());
        updatableProduct.setDescription(newProduct.getDescription());
        updatableProduct.setPrice(newProduct.getPrice());
        updatableProduct.setWeight(newProduct.getWeight());

        return productRepository.save(updatableProduct);
    }

}
