package com.inventory.pos.service;

import com.inventory.pos.dao.InvoiceDetailRepository;
import com.inventory.pos.dao.InvoiceRepository;
import com.inventory.pos.dao.ProductRepository;
import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import com.inventory.pos.entity.Product;
import com.inventory.pos.request.CreateInvoiceDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailService {

    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public InvoiceDetail createInvoiceDetail(CreateInvoiceDetailRequest createInvoiceDetailRequest) throws ParseException {

         Long invoiceId=createInvoiceDetailRequest.getInvoiceId();

Optional<Invoice> invoice=this.invoiceRepository.findById(invoiceId);

        Long productId=createInvoiceDetailRequest.getProductId();
        Optional<Product>product=this.productRepository.findById(productId);
        InvoiceDetail invoiceDetail=new InvoiceDetail(createInvoiceDetailRequest);

        invoiceDetail.setInvoice(invoice.get());
        invoiceDetail.setProduct(product.get());

       invoiceDetailRepository.save(invoiceDetail);
       return invoiceDetail;
    }

    public List<InvoiceDetail> findAllByInvoiceId(Long id){
        return invoiceDetailRepository.findAllByInvoiceId(id);
    }

    public String deleteInvoice(Long id){
        Invoice invoice=invoiceRepository.findById(id).get();
        String customerName=invoice.getCustomerName();
        invoiceRepository.deleteById(id);
        return customerName;
    }
}
