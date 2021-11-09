package com.inventory.pos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.pos.request.CreateInvoiceDetailRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

//    @Column(name="image_name")
//    private String imageName;

//    @Column(name="image_url")
//    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<InvoiceDetail> invoiceDetails=new HashSet<>();






}
