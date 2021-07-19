package com.product.productManager.domain.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 150)
    private String description;

    @NotNull
    private Double weight;

    @NotNull
    private Double price;

    @NotNull
    @Size(min = 3, max = 30)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!name.equals(product.name)) return false;
        if (!description.equals(product.description)) return false;
        if (!weight.equals(product.weight)) return false;
        if (!price.equals(product.price)) return false;
        return country.equals(product.country);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
