package com.inventory.pos.service;

import com.inventory.pos.dao.InvoiceDetailRepository;
import com.inventory.pos.dao.InvoiceRepository;
import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import com.inventory.pos.entity.Product;
import com.inventory.pos.request.CreateInvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;


    public Invoice createInvoice(CreateInvoiceRequest createInvoiceRequest) throws ParseException {



      Invoice invoice=new Invoice(createInvoiceRequest);
      invoice=invoiceRepository.save(invoice);

        return invoice;
    }

}
