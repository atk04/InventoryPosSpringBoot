package com.inventory.pos.request;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class InvoiceDetailItem {

     private String productName;
    private int productQuantity;
    private BigDecimal productPrice;
    private Date orderDate;

}
