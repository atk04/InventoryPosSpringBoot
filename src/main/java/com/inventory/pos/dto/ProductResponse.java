package com.inventory.pos.dto;

import com.inventory.pos.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private int stock;
    private String description;

    public ProductResponse(Product product){
        this.id=product.getId();
        this.name=product.getName();
        this.purchasePrice=product.getPurchasePrice();
        this.salePrice=product.getSalePrice();
        this.stock=product.getStock();
        this.description=product.getDescription();
    }
}
