package com.inventory.pos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="purchase_price")
    private BigDecimal purchasePrice;

    @Column(name="sale_price")
    private BigDecimal salePrice;

    @Column(name="stock")
    private int stock;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;





}
