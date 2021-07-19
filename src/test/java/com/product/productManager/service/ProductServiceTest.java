package com.product.productManager.service;

import com.product.productManager.domain.model.Product;
import com.product.productManager.domain.repository.ProductRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.product.productManager")
@EnableJpaRepositories("com.product.productManager.domain.repository")
public class ProductServiceTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @After
    public void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    public void testNotNullRepository(){
        Assert.assertNotNull(productRepository);
    }

    @Test
    public void testGetProduct() {
        Product product = productRepository.save(new Product("Iphone X", "Used iphone with some scratches", 8.04, 300.99, "United States" ));

        Product savedProduct = productService.getProduct(product.getId());

        Assert.assertEquals(product, savedProduct);
    }

    @Test
    public void teatGetAll() {
        Product product = productRepository.save(new Product("Iphone X", "Used iphone with some scratches", 8.04, 300.99, "United States" ));
        Product product2 = productRepository.save(new Product("Iphone XI", "Used iphone Brand New", 9.04, 400.99, "United States" ));

        productService.getAllPagedAndSorted().forEach(dbProduct -> Assert.assertTrue(dbProduct.equals(product) || dbProduct.equals(product2)));
    }

    @Test
    public void teatNewProduct() {
        Product newProduct = productService.newProduct(new Product("Iphone XI", "Used iphone Brand New", 9.04, 400.99, "United States" ));

        Assert.assertTrue(productRepository.findById(newProduct.getId()).isPresent());

    }

    @Test
    public void testDeleteProduct() {
        Product product = productRepository.save(new Product("Iphone X", "Used iphone with some scratches", 8.04, 300.99, "United States" ));

        productService.deleteProduct(product.getId());

        Assert.assertFalse(productRepository.findById(product.getId()).isPresent());

    }

    @Test
    public void teatUpdateProduct() {
        Product product = productRepository.save(new Product("Iphone X", "Used iphone with some scratches", 8.04, 300.99, "United States" ));
        product.setPrice(350.00);
        productService.updateProduct(product.getId(), product);

        Assert.assertEquals(350.00, productRepository.findById(product.getId()).get().getPrice().doubleValue(), 1e-5);
    }

    @Test
    public void testFindByName() {
        Product product = productRepository.save(new Product("Iphone X", "Used iphone with some scratches", 8.04, 300.99, "United States" ));

        Assert.assertEquals(product, productRepository.findByName("Iphone X").get(0));
    }
}