package com.inventory.pos.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class SaleResponse {
    private Long id;
    private BigDecimal totalSale;
    private Date orderDate;



}
