package com.inventory.pos.dao;

import com.inventory.pos.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "invoice",path="invoice")
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

}

