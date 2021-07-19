package com.product.productManager.service;

import com.product.productManager.domain.model.Product;
import com.product.productManager.domain.repository.ProductRepository;
import com.product.productManager.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(int id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Product id: " + id +" not found."));
    }

    public List<Product> getAllPagedAndSorted(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromOptionalString(order).get(), "name");
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> getAllPagedAndSorted() {
        return Streamable.of(productRepository.findAll()).toList();
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

        updateProduct(newProduct, updatableProduct);

        return productRepository.save(updatableProduct);
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    private void updateProduct(Product newProduct, Product updatableProduct) {
        updatableProduct.setName(newProduct.getName());
        updatableProduct.setDescription(newProduct.getDescription());
        updatableProduct.setPrice(newProduct.getPrice());
        updatableProduct.setWeight(newProduct.getWeight());
    }
}
