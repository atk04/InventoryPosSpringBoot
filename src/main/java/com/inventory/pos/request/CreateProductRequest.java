package com.inventory.pos.request;

import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private Product product;
    private ProductCategory category;
}
