package com.inventory.pos.controller;

import com.inventory.pos.dto.ProductResponse;
import com.inventory.pos.dto.ResponseMessage;
import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import com.inventory.pos.request.CreateProductRequest;
import com.inventory.pos.service.FilesStorageService;
import com.inventory.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    ProductService productService;

    @PostMapping(value = "create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity createProduct(@RequestParam("productImage") MultipartFile productImage, @RequestPart CreateProductRequest createProductRequest) {
        String message = "";
        //for product name generation
        String productCodePrefix = "-PRODUCT-";
        String codeDigit = "000000";
        String zeroRegex = "^0+(?!$)";
        String productGeneratedId = "";
        String removePrefixString = "";
        String removeZeroString = "";
        int idCount = 0;
        String productName = "";

        Long productCategoryId = createProductRequest.getCategory().getId();

        List<Product> getAllProductByCategory = productService.getAllProductByCategoryId(productCategoryId);

        Long categoryId = createProductRequest.getCategory().getId();

        Optional<ProductCategory> getProductCategory = productService.getAllProductCategoryById(categoryId);
        String categoryNameForProductImage = getProductCategory.get().getCategoryName();


        if (getAllProductByCategory.isEmpty()) {
            productGeneratedId = "0";
            productName = createProductRequest.getProduct().getName() + productCodePrefix + codeDigit + productGeneratedId+".png";
        } else {
            for (int i = 0; i < getAllProductByCategory.size(); i++) {
                productName = getAllProductByCategory.get(i).getImageName();
            }
        }

        int dotCharPosition = productName.indexOf('.');

        productName = productName.substring(0, dotCharPosition);

        removePrefixString = productName.substring(productName.indexOf('-') + productCodePrefix.length());
        removeZeroString = removePrefixString.replaceAll(zeroRegex, "");


        if (removeZeroString.length() == 1) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit + idCount + ".png");
        }
        if (removeZeroString.length() == 2) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit.substring(0) + idCount + ".png");
        }
        if (removeZeroString.length() == 3) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit.substring(1) + idCount + ".png");
        }
        if (removeZeroString.length() == 4) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit.substring(2) + idCount + ".png");
        }
        if (removeZeroString.length() == 5) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit.substring(3) + idCount + ".png");
        }
        if (removeZeroString.length() == 6) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit.substring(4) + idCount + ".png");
        }
        if (removeZeroString.length() == 7) {
            idCount = Integer.parseInt((removeZeroString)) + 1;
            createProductRequest.getProduct().setImageName(categoryNameForProductImage + productCodePrefix + codeDigit.substring(5) + idCount + ".png");
        }




        try {


            storageService.save(productImage, createProductRequest.getProduct().getImageName());

            String filename = productImage.getOriginalFilename();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ProductController.class, "getFile", createProductRequest.getProduct().getImageName().toString()).build().toString();
            //createProductRequest.getProduct().setImageName(productName);
            createProductRequest.getProduct().setImageUrl(url);
            Product product = productService.createProduct(createProductRequest);
            message = createProductRequest.getProduct().getName();
        } catch (Exception e) {
            message = "Could not upload the file: " + productImage.getOriginalFilename() + "!";
        }


        // ProductResponse productResponse= new ProductResponse(product);


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteProductCategory(Long id) {
        String result = productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(result));
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
