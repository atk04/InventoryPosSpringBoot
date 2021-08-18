package com.inventory.pos.controller;

import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductCategoryRequest;
import com.inventory.pos.request.UpdateProductCategoryRequest;
import com.inventory.pos.dto.ProductCategoryResponse;
import com.inventory.pos.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/")
@CrossOrigin("http://localhost:4200")
public class ProductCategoryController {
@Autowired
    ProductCategoryService productCategoryService;


@PostMapping("create")
    public ProductCategoryResponse createProductCategory(@RequestBody CreateProductCategoryRequest createProductCategoryRequest) {
    ProductCategory productCategory=productCategoryService.createProductCategory(createProductCategoryRequest);
    return new ProductCategoryResponse(productCategory);
}

@PutMapping("update")
    public ProductCategoryResponse updateProductCategory(@RequestBody UpdateProductCategoryRequest updateProductCategoryRequest){
    ProductCategory productCategory= productCategoryService.updateProductCategory(updateProductCategoryRequest);
    return new ProductCategoryResponse(productCategory);
}
}
