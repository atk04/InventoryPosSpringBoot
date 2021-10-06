package com.inventory.pos.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.pos.request.CreateInvoiceDetailRequest;
import com.inventory.pos.request.InvoiceDetailItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name="invoice_detail")
@Getter
@Setter
@NoArgsConstructor
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_quantity")
    private int productQuantity;

    @Column(name="product_price")
    private BigDecimal productPrice;

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Column(name="order_date")
    private Date orderDate;

    public InvoiceDetail(CreateInvoiceDetailRequest createInvoiceDetailRequest) throws ParseException {

       this.productName=createInvoiceDetailRequest.getInvoiceDetailItem().getProductName();
       this.productQuantity=createInvoiceDetailRequest.getInvoiceDetailItem().getProductQuantity();
       this.productPrice=createInvoiceDetailRequest.getInvoiceDetailItem().getProductPrice();
        this.orderDate=createInvoiceDetailRequest.getInvoiceDetailItem().getOrderDate();


    }

}
