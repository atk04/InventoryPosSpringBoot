package com.inventory.pos.controller;

import com.inventory.pos.dto.InvoiceResponse;
import com.inventory.pos.dto.ResponseMessage;
import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import com.inventory.pos.request.CreateInvoiceDetailRequest;
import com.inventory.pos.request.CreateInvoiceRequest;
import com.inventory.pos.service.InvoiceDetailService;
import com.inventory.pos.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/order/")
@CrossOrigin("http://localhost:4200")
public class OrderController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceDetailService invoiceDetailService;

    @PostMapping("createInvoice")
    public InvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest createInvoiceRequest) throws ParseException {
        Invoice invoice=invoiceService.createInvoice(createInvoiceRequest);
        return new InvoiceResponse(invoice);
    }

    @PostMapping("createInvoiceDetail")
    public ResponseEntity createInvoiceDetail(@RequestBody CreateInvoiceDetailRequest createInvoiceDetailRequest) throws ParseException {
        InvoiceDetail invoiceDetail=invoiceDetailService.createInvoiceDetail(createInvoiceDetailRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success"));
    }

    @GetMapping("orderList")
    public List<Invoice> getAllOrderList(){
        List<Invoice> invoiceList=this.invoiceService.getAllOrder();
        return invoiceList;
    }


}
