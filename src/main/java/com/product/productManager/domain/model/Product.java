package com.product.productManager.domain.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double price;
    private String country;

    public Product() {
    }

    public Product(String name, String description, Double weight, Double price, String country) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
