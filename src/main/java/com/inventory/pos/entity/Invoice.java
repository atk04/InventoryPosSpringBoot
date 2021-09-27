package com.inventory.pos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
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

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "invoice")
    private Set<InvoiceDetail> invoiceDetail=new HashSet<>();

    public Invoice(CreateInvoiceRequest createInvoiceRequest) throws ParseException {
        this.customerName=createInvoiceRequest.getInvoice().getCustomerName();
        this.orderDate=createInvoiceRequest.getInvoice().getOrderDate();
        this.subTotal=createInvoiceRequest.getInvoice().getSubTotal();
        this.tax=createInvoiceRequest.getInvoice().getTax();
        this.discount=createInvoiceRequest.getInvoice().getDiscount();
        this.total=createInvoiceRequest.getInvoice().getTotal();
        this.paid=createInvoiceRequest.getInvoice().getPaid();
        this.due=createInvoiceRequest.getInvoice().getDue();
        this.paymentType=createInvoiceRequest.getInvoice().getPaymentType();
    }



}
