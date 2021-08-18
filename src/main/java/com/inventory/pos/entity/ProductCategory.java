package com.inventory.pos.entity;

import com.inventory.pos.request.CreateProductCategoryRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="product_category")
@Getter
@Setter
@NoArgsConstructor
public class ProductCategory {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;

    public ProductCategory(CreateProductCategoryRequest createProductCategoryRequest){
        this.categoryName=createProductCategoryRequest.getCategoryName();
    }
}
