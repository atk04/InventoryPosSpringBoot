package com.inventory.pos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.pos.request.SaleRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="sale")
@Getter
@Setter
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="total_sale")
    private BigDecimal totalSale;

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Column(name="order_date")
    private Date orderDate;

    public Sale(SaleRequest saleRequest){
        this.totalSale=saleRequest.getTotalSale();
        this.orderDate=saleRequest.getOrderDate();
    }
}
