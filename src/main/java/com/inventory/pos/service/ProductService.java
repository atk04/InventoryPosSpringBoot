package com.inventory.pos.service;

import com.inventory.pos.dao.ProductCategoryRepository;
import com.inventory.pos.dao.ProductRepository;
import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public Product createProduct(CreateProductRequest createProductRequest){
        ProductCategory productCategory=createProductRequest.getCategory();
        Product product=createProductRequest.getProduct();
        product.setProductCategory(productCategory);
        productRepository.save(product);
        return product;
    }

    public String deleteProduct(Long id) {
        Product product=productRepository.findById(id).get();
        String productName=product.getName();
        String ImageName=product.getImageName();
        productRepository.deleteById(id);
        storageService.deleteProductImage(ImageName);
        return productName;
    }

    public List<Product> getAllProduct(){

        return productRepository.findAll();

    }

    public  List<Product>getAllProductByCategoryId(Long id){
        return productRepository.findProductByProductCategoryId(id);
    }

    public Optional<ProductCategory>getAllProductCategoryById(Long id){
        Optional<ProductCategory> category= productCategoryRepository.findById(id);
        return category;
    }
}
