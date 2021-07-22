package com.product.productManager.domain.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(indexes = @Index(name = "product_index", columnList = "name"))
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String productID;

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

    @NotNull
    private Date createdAt;

    @NotNull
    private Date lastUpdate;

    @NotNull
    @Size(min = 3, max = 50)
    private String createdBy;

    @NotNull
    @Size(min = 3, max = 50)
    private String lastModifyBy;

}
