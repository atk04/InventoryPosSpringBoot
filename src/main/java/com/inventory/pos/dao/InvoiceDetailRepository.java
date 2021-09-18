package com.inventory.pos.dao;

import com.inventory.pos.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "invoiceDetail",path="invoice-detail")
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long> {
}
