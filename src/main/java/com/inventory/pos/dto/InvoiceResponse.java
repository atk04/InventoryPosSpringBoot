package com.inventory.pos.dto;

import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class InvoiceResponse {
    private Long id;
    private String customerName;
    private Date orderDate;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal total;
    private BigDecimal paid;
    private BigDecimal due;
    private String paymentType;

    public InvoiceResponse(Invoice invoice){
        this.id=invoice.getId();
        this.customerName=invoice.getCustomerName();
        this.orderDate=invoice.getOrderDate();
        this.subTotal=invoice.getSubTotal();
        this.tax=invoice.getTax();
        this.discount=invoice.getDiscount();
        this.total=invoice.getTotal();
        this.paid=invoice.getPaid();
        this.due=invoice.getDue();
        this.paymentType=invoice.getPaymentType();
    }


}
