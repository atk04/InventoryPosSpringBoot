package com.inventory.pos.controller;

import com.inventory.pos.dto.ProductResponse;
import com.inventory.pos.dto.ResponseMessage;
import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductRequest;
import com.inventory.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/")
@CrossOrigin("http://localhost:4200")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("create")
    public ProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest){

       Product product= productService.createProduct(createProductRequest);
        ProductResponse productResponse= new ProductResponse(product);
        return productResponse;
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteProductCategory(Long id){
        String result=productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(result));
    }

}
