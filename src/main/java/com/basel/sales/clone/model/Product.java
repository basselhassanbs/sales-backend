package com.basel.sales.clone.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(name = "name")
    private String name;
    @NotEmpty
    @Column(name = "description")
    private String description;
    @NotEmpty
    @Column(name = "category")
    private String category;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "creation_date")
    private Date creationDate;
    @NotNull
    @Column(name = "quantity")
    private Integer quantity;
    @NotNull
    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "products")
    private Set<Sale> sales = new HashSet<>();

    public Product() {
    }

    public Product(String name, String description, String category, Integer quantity, Double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.creationDate = new Date();
        this.quantity = quantity;
        this.price = price;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
