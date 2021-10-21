package com.inventory.pos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class SaleRequest {
    private BigDecimal totalSale;
    private Date orderDate;
}
