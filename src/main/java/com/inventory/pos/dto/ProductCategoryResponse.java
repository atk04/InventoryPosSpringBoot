package com.inventory.pos.dto;

import com.inventory.pos.entity.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryResponse {
    private Long id;
    private String categoryName;

    public ProductCategoryResponse(ProductCategory productCategory){
        this.id=productCategory.getId();
        this.categoryName=productCategory.getCategoryName();
    }
}
