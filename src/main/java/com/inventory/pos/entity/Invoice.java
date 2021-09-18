package com.inventory.pos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.pos.request.CreateInvoiceDetailRequest;
import com.inventory.pos.request.CreateInvoiceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="invoice")
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="customer_name")
    private String customerName;

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="order_date")
    private Date orderDate;

    @Column(name="subtotal")
    private BigDecimal subTotal;

    @Column(name="tax")
    private BigDecimal tax;

    @Column(name="discount")
    private BigDecimal discount;

    @Column(name="total")
    private BigDecimal total;

    @Column(name="paid")
    private BigDecimal paid;

    @Column(name="due")
    private BigDecimal due;

    @Column(name="payment_type")
    private String paymentType;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "invoice")
    private Set<InvoiceDetail> invoiceDetail=new HashSet<>();

    public Invoice(CreateInvoiceRequest createInvoiceRequest) throws ParseException {
        this.customerName=createInvoiceRequest.getCustomerName();
        this.orderDate=createInvoiceRequest.getOrderDate();
        this.subTotal=createInvoiceRequest.getSubTotal();
        this.tax=createInvoiceRequest.getTax();
        this.discount=createInvoiceRequest.getDiscount();
        this.total=createInvoiceRequest.getTotal();
        this.paid=createInvoiceRequest.getPaid();
        this.due=createInvoiceRequest.getDue();
        this.paymentType=createInvoiceRequest.getPaymentType();
    }



}
