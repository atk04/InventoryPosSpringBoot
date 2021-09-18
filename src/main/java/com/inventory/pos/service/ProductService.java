package com.inventory.pos.service;

import com.inventory.pos.dao.ProductCategoryRepository;
import com.inventory.pos.dao.ProductRepository;
import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductRequest;
import com.inventory.pos.request.UpdateProductRequest;
import com.inventory.pos.request.UpdateProductStockRequest;
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

    public String updateProduct(UpdateProductRequest updateProductRequest){
        Long productId=updateProductRequest.getProduct().getId();
        Optional<Product> product=productRepository.findById(productId);
        product.get().setName(updateProductRequest.getProduct().getName());
        product.get().setPurchasePrice(updateProductRequest.getProduct().getPurchasePrice());
        product.get().setSalePrice(updateProductRequest.getProduct().getSalePrice());
        product.get().setStock(updateProductRequest.getProduct().getStock());
        product.get().setDescription(updateProductRequest.getProduct().getDescription());
        product.get().setImageName(updateProductRequest.getProduct().getImageName());
        product.get().setImageUrl(updateProductRequest.getProduct().getImageUrl());
        ProductCategory productCategory=updateProductRequest.getCategory();
        product.get().setProductCategory(productCategory);
        String productName=updateProductRequest.getProduct().getName();
        productRepository.save(product.get());
        return productName;
    }

    public String updateProductStock(UpdateProductStockRequest updateProductStockRequest){
        Long productId=updateProductStockRequest.getId();
        Optional<Product> product=productRepository.findById(productId);
        product.get().setStock(updateProductStockRequest.getStock());
        productRepository.save(product.get());
        return "success";
    }

    public void deleteProductImage(Long id){
        Optional<Product> product=productRepository.findById(id);
        //delete image from upload directory
        storageService.deleteProductImage(product.get().getImageName());
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
