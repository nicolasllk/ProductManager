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

import java.util.Date;
import java.util.List;

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
        Product product = productRepository.save(new Product(null, "Iphone X", "Used iphone with some scratches", 8.04,
                300.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));

        Product savedProduct = productService.getProduct(product.getId());

        Assert.assertEquals(product, savedProduct);
    }

    @Test
    public void teatGetAll() {
        Product product = productRepository.save(new Product(null, "Iphone X", "Used iphone with some scratches",
                8.04, 300.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));
        Product product2 = productRepository.save(new Product(null, "Iphone XI", "Used iphone Brand New",
                9.04, 400.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));

        List<Product> resultSet = productService.getAllPagedAndSorted();
        Assert.assertEquals(2, resultSet.size());
        Assert.assertTrue(resultSet.get(0).getName().equals("Iphone X"));
        Assert.assertTrue(resultSet.get(1).getName().equals("Iphone XI"));
    }

    @Test
    public void teatNewProduct() {
        Product newProduct = productService.newProduct(new Product(null, "Iphone XI", "Used iphone Brand New", 9.04,
                400.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));

        Assert.assertTrue(productRepository.findById(newProduct.getId()).isPresent());

    }

    @Test
    public void testDeleteProduct() {
        Product product = productRepository.save(new Product(null, "Iphone X", "Used iphone with some scratches",
                8.04, 300.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));

        productService.deleteProduct(product.getId());

        Assert.assertFalse(productRepository.findById(product.getId()).isPresent());

    }

    @Test
    public void teatUpdateProduct() {
        Product product = productRepository.save(new Product(null, "Iphone X", "Used iphone with some scratches",
                8.04, 300.99, "United States" , new Date(), new Date(), "dummyUser", "dummyUser"));
        product.setPrice(350.00);
        productService.updateProduct(product.getId(), product);

        Assert.assertEquals(350.00, productRepository.findById(product.getId()).get().getPrice().doubleValue(), 1e-5);
    }

    @Test
    public void testFindByName() {
        Product product = productRepository.save(new Product(null,"Iphone X", "Used iphone with some scratches",
                8.04, 300.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));

        Assert.assertEquals(product, productRepository.findByName("Iphone X").get(0));
    }
}