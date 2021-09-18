package com.inventory.pos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStockRequest {
    private Long id;
    private int stock;
}
