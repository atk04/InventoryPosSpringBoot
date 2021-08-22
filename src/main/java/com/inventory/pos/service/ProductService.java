package com.inventory.pos.service;

import com.inventory.pos.dao.ProductRepository;
import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(CreateProductRequest createProductRequest){
        ProductCategory productCategory=createProductRequest.getCategory();
        Product product=createProductRequest.getProduct();
        product.setProductCategory(productCategory);
        productRepository.save(product);
        return product;
    }
}
