package com.inventory.pos.request;

import com.inventory.pos.entity.Company;
import com.inventory.pos.entity.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CreateInvoiceRequest {


    private String customerName;
    private Date orderDate;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal total;
    private BigDecimal paid;
    private BigDecimal due;
    private String paymentType;
    private Long companyId;
}
