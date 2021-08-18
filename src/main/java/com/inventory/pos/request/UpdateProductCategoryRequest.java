package com.inventory.pos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductCategoryRequest {
    private Long id;
    private String categoryName;
}
