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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

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
    public Page<Invoice> getAllOrderList(@RequestParam Optional<Integer>page,@RequestParam Optional<Integer>size, @RequestParam Optional<String> sortBy){
        Page<Invoice> invoiceList=this.invoiceService.getAllOrder(page,size,sortBy);
        return invoiceList;

    }
    @GetMapping("findAllByInvoiceId")
    public List<InvoiceDetail> findAllByInvoiceId(@RequestParam Long id){
        return this.invoiceDetailService.findAllByInvoiceId(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteInvoice(@RequestParam Long id){
        System.out.println( id);
        String result=invoiceDetailService.deleteInvoice(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(result));
    }




}
