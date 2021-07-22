package com.product.productManager.domain.repository;

import com.product.productManager.domain.model.Product;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.product.productManager")
@EnableJpaRepositories("com.product.productManager.domain.repository")
public class ProductRepositoryPersistenceTest {
    @Autowired
    ProductRepository productRepository;

    @After
    public void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    public void testNotNullRepository(){
        Assert.assertNotNull(productRepository);
    }

    @Test
    public void testPersistNewProduct(){
        Product product = productRepository.save(new Product(null, null,"Iphone X", "Used iphone with some scratches", 8.04,
                300.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser" ));
        Assert.assertNotNull(product);
        Assert.assertEquals("Iphone X", product.getName());
        Assert.assertEquals("Used iphone with some scratches", product.getDescription());
        Assert.assertEquals(8.04, product.getWeight().doubleValue(), 1e-5);
        Assert.assertEquals(300.99, product.getPrice().doubleValue(), 1e-5);
        Assert.assertEquals("United States", product.getCountry());

    }

    @Test
    public void testLoadPersistedProduct(){
       productRepository.save(new Product(null, null, "Iphone X", "Used iphone with some scratches", 8.04, 300.99, "United States"
               , new Date(), new Date(), "dummyUser", "dummyUser"));
       Product product = productRepository.findAll().iterator().next();

       Assert.assertNotNull(product);
       Assert.assertEquals("Iphone X", product.getName());
       Assert.assertEquals("Used iphone with some scratches", product.getDescription());
       Assert.assertEquals(8.04, product.getWeight().doubleValue(), 1e-5);
       Assert.assertEquals(300.99, product.getPrice().doubleValue(), 1e-5);
       Assert.assertEquals("United States", product.getCountry());

    }

    @Test
    public void testEditProduct(){
        productRepository.save(new Product(null, null,  "Iphone X", "Used iphone with some scratches",
                8.04, 300.99, "United States", new Date(), new Date(), "dummyUser", "dummyUser"));
        Product product = productRepository.findAll().iterator().next();
        int entityId = product.getId();
        product.setDescription("Good as new");
        productRepository.save(product);

        Product editedProduct = productRepository.findById(entityId).get();
        Assert.assertNotNull(product);
        Assert.assertEquals("Iphone X", editedProduct.getName());
        Assert.assertEquals("Good as new", editedProduct.getDescription());
        Assert.assertEquals(8.04, editedProduct.getWeight().doubleValue(), 1e-5);
        Assert.assertEquals(300.99, editedProduct.getPrice().doubleValue(), 1e-5);
        Assert.assertEquals("United States", editedProduct.getCountry());

    }

    @Test
    public void testDeleteProduct(){
        productRepository.save(new Product(null, null, "Iphone XI", "Used iphone with some scratches", 8.04, 300.99, "United States"
                , new Date(), new Date(), "dummyUser", "dummyUser"));
        AtomicInteger productId = new AtomicInteger();
        productRepository.findAll().forEach(product1 -> {
            if (product1.getName().equals("Iphone XI")) {
                productId.set(product1.getId());
            }
        });

        productRepository.deleteById(productId.get());
        Assert.assertFalse(productRepository.findById(productId.get()).isPresent());
    }

    @Test
    public void testFindByProductId(){
        productRepository.save(new Product(null, "Iphone_XI_001", "Iphone XI", "Used iphone with some scratches", 8.04, 300.99, "United States"
                , new Date(), new Date(), "dummyUser", "dummyUser"));

        Assert.assertTrue(productRepository.findByProductID("Iphone_XI_001").isPresent());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testFindByProductIdUniqueViolation(){
        productRepository.save(new Product(null, "Iphone_XI_001", "Iphone XI", "Used iphone with some scratches", 8.04, 300.99, "United States"
                , new Date(), new Date(), "dummyUser", "dummyUser"));
        productRepository.save(new Product(null, "Iphone_XI_001", "Iphone XI GOOD", "Used iphone with some scratches", 9.04, 390.99, "United States"
                , new Date(), new Date(), "dummyUser", "dummyUser"));

    }

}