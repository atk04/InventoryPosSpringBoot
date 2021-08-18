package com.inventory.pos.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateProductCategoryRequest {

    private String categoryName;
}
