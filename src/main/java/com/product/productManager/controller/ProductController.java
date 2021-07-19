package com.product.productManager.controller;

import com.product.productManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.product.productManager.domain.model.Product;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @GetMapping( produces = "application/json")
    public List<Product> getProduct(){
        return productService.getAll();
    }

    @PostMapping(produces = "application/json")
    public Product newProduct(@RequestBody Product newProduct) {
        return productService.newProduct(newProduct);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updateProduct) {
        return productService.updateProduct(id, updateProduct);
    }
}
