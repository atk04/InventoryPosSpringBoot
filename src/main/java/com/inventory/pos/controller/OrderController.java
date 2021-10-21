package com.inventory.pos.controller;

import com.inventory.pos.dto.InvoiceResponse;
import com.inventory.pos.dto.ResponseMessage;
import com.inventory.pos.dto.SaleResponse;
import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import com.inventory.pos.entity.Sale;
import com.inventory.pos.request.*;
import com.inventory.pos.service.InvoiceDetailService;
import com.inventory.pos.service.InvoiceService;
import com.inventory.pos.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    SaleService saleService;

    @PostMapping("createInvoice")
    public InvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest createInvoiceRequest) throws ParseException {
        Invoice invoice=invoiceService.createInvoice(createInvoiceRequest);
        return new InvoiceResponse(invoice);
    }

    @PutMapping("updateInvoice")
    public InvoiceResponse updateInvoice(@RequestBody UpdateInvoiceRequest updateInvoiceRequest){
        Invoice invoice=invoiceService.updateInvoice(updateInvoiceRequest);
        return new InvoiceResponse(invoice);
    }

    @PostMapping("createInvoiceDetail")
    public ResponseEntity createInvoiceDetail(@RequestBody CreateInvoiceDetailRequest createInvoiceDetailRequest) throws ParseException {
        InvoiceDetail invoiceDetail=invoiceDetailService.createInvoiceDetail(createInvoiceDetailRequest);

       Sale currentSale=saleService.getSaleByOrderDate(createInvoiceDetailRequest.getInvoiceDetailItem().getOrderDate());
        Date orderDate = createInvoiceDetailRequest.getInvoiceDetailItem().getOrderDate();
        if(currentSale==null) {

            List<InvoiceDetail> invoiceDetails=invoiceDetailService.findAllByOrderDate(orderDate);
            if(!invoiceDetails.isEmpty()){
                BigDecimal productPrice=BigDecimal.ZERO;
                int productQuantity=0;
                BigDecimal currentTotalSale= new BigDecimal(0.0);
                BigDecimal totalSale=BigDecimal.ZERO;


                for(int i=0;i<invoiceDetails.size();i++){

                    productPrice=invoiceDetails.get(i).getProductPrice();
                    productQuantity=invoiceDetails.get(i).getProductQuantity();
                    currentTotalSale=productPrice.multiply(BigDecimal.valueOf(productQuantity));
                    totalSale=totalSale.add(currentTotalSale);
                }
                SaleRequest sale = new SaleRequest();
                sale.setTotalSale(totalSale);
                sale.setOrderDate(orderDate);
                saleService.createSale(sale);
            }else{
                int productQuantity = createInvoiceDetailRequest.getInvoiceDetailItem().getProductQuantity();
                BigDecimal productPrice = createInvoiceDetailRequest.getInvoiceDetailItem().getProductPrice();
                BigDecimal totalSale = new BigDecimal(productQuantity);
                totalSale = totalSale.multiply(productPrice);
                SaleRequest sale = new SaleRequest();
                sale.setTotalSale(totalSale);
                sale.setOrderDate(orderDate);
                saleService.createSale(sale);
            }

        }else{
            BigDecimal productPrice=BigDecimal.ZERO;
            int productQuantity=0;
            BigDecimal currentTotalSale= new BigDecimal(0.0);
            BigDecimal totalSale=BigDecimal.ZERO;
            List<InvoiceDetail> invoiceDetails=invoiceDetailService.findAllByOrderDate(orderDate);
            if(!invoiceDetails.isEmpty()) {
                for (int i = 0; i < invoiceDetails.size(); i++) {

                    productPrice = invoiceDetails.get(i).getProductPrice();
                    productQuantity = invoiceDetails.get(i).getProductQuantity();
                    currentTotalSale = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                    totalSale = totalSale.add(currentTotalSale);
                }


                UpdateSaleRequest updateSaleRequest = new UpdateSaleRequest();
                updateSaleRequest.setId(currentSale.getId());
                updateSaleRequest.setTotalSale(totalSale);
                updateSaleRequest.setOrderDate(currentSale.getOrderDate());

                saleService.updateSale(updateSaleRequest);
            }else{
                int productQuantity1 = createInvoiceDetailRequest.getInvoiceDetailItem().getProductQuantity();
                BigDecimal productPrice1 = createInvoiceDetailRequest.getInvoiceDetailItem().getProductPrice();
                BigDecimal totalSale1 = new BigDecimal(productQuantity1);
                totalSale1 = totalSale1.multiply(productPrice1);
                SaleRequest sale = new SaleRequest();
                sale.setTotalSale(totalSale1);
                sale.setOrderDate(orderDate);
                saleService.createSale(sale);
            }
        }
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

    @GetMapping("getRecentBuyer")
    public List<Invoice>getRecentBuyer(){
        return this.invoiceService.getRecentBuyer();
    }



    @DeleteMapping("delete")
    public ResponseEntity deleteInvoice(@RequestParam Long id){
        //System.out.println( id);
        String result=invoiceDetailService.deleteInvoice(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(result));
    }

    @DeleteMapping("deleteInvoiceDetail")
    public void deleteInvoiceDetailByInvoiceId(@RequestParam Long id){
        invoiceDetailService.deleteInvoiceDetailByInvoiceId(id);
    }




}
