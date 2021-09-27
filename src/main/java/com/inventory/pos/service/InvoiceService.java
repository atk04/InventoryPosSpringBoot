package com.inventory.pos.service;

import com.inventory.pos.dao.InvoiceDetailRepository;
import com.inventory.pos.dao.InvoiceRepository;
import com.inventory.pos.entity.Company;
import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import com.inventory.pos.entity.Product;
import com.inventory.pos.request.CreateInvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;


    public Invoice createInvoice(CreateInvoiceRequest createInvoiceRequest) throws ParseException {
      Invoice invoice=new Invoice(createInvoiceRequest);
      Company company=createInvoiceRequest.getCompany();
      invoice.setCompany(company);
      invoice=invoiceRepository.save(invoice);

        return invoice;
    }

    public Page<Invoice> getAllOrder(@RequestParam Optional<Integer>page,@RequestParam Optional<Integer>size, @RequestParam Optional<String> sortBy){
        return invoiceRepository.findAll(
                PageRequest.of(
                page.orElse(0),size.orElse(20), Sort.Direction.DESC,sortBy.orElse("id")
                )
        );

    }

}
