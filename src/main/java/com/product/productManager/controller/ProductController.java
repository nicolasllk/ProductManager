package com.product.productManager.controller;

import com.product.productManager.domain.dto.ProductDTO;
import com.product.productManager.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.product.productManager.domain.model.Product;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/{id}", produces = "application/json")
    public ProductDTO getProduct(@PathVariable int id){
        return mapToDTO(productService.getProduct(id));
    }

    @GetMapping( params = { "page", "size", "order" }, produces = "application/json")
    public List<ProductDTO> getProduct(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam String order){

        return productService.getAllPagedAndSorted(page, size, order).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping( value = "/name/{name}", produces = "application/json")
    public List<ProductDTO> getProductByName(@PathVariable String name) {
        return productService.getProductByName(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(  produces = "application/json")
    public List<ProductDTO> getProduct(){
        return productService.getAllPagedAndSorted().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(produces = "application/json")
    public ProductDTO newProduct(@RequestBody ProductDTO newProductDTO) {
        Product product = mapToProduct(newProductDTO);
        return mapToDTO(productService.newProduct(product));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void removeProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ProductDTO updateProduct(@PathVariable int id, @RequestBody ProductDTO updateProductDTO) {
        Product updateProduct = mapToProduct(updateProductDTO);
        return mapToDTO(productService.updateProduct(id, updateProduct));
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    private Product mapToProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Date now = new Date();
        product.setCreatedAt(now);
        product.setLastUpdate(now );
        product.setCreatedBy("UserGottenFromContext");
        product.setLastModifyBy("UserGottenFromContext");

        return product;
    }
}
