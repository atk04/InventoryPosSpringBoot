package com.inventory.pos.dao;

import com.inventory.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productList",path="product-list")
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByProductCategoryId(Long id);
}
