package com.inventory.pos.service;

import com.inventory.pos.dao.ProductCategoryRepository;
import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductCategoryRequest;
import com.inventory.pos.request.UpdateProductCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(CreateProductCategoryRequest createProductCategoryRequest){
        ProductCategory productCategory=new ProductCategory(createProductCategoryRequest);
        productCategory=productCategoryRepository.save(productCategory);
        return productCategory;
    }

    public ProductCategory updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest){
        ProductCategory productCategory=productCategoryRepository.findById(updateProductCategoryRequest.getId()).get();
        productCategory.setCategoryName(updateProductCategoryRequest.getCategoryName());
        productCategoryRepository.save(productCategory);
        return productCategory;
    }

    public String deleteProductCategory(Long id){
        ProductCategory category=productCategoryRepository.findById(id).get();
        String productCategoryName=category.getCategoryName();
        productCategoryRepository.deleteById(id);
        return productCategoryName;
    }

}
