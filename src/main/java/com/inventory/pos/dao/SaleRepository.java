package com.inventory.pos.dao;

import com.inventory.pos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "sale",path="sale")
public interface SaleRepository extends JpaRepository<Sale,Long> {
Sale findAllByOrderDate(Date orderDate);
List<Sale>findTop10ByOrderByOrderDate();
}
