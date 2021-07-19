package com.product.productManager.controller;

import com.product.productManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.productManager.domain.model.Product;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @PostMapping(produces = "application/json")
    public Product newProduct(@RequestBody Product newProduct) {
        return productService.newProduct(newProduct);
    }

    @DeleteMapping(produces = "application/json")
    public void removeProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    public Product updateProduct(@PathVariable int id, @RequestBody Product updateProduct) {
        return productService.updateProduct(id, updateProduct);
    }
}
