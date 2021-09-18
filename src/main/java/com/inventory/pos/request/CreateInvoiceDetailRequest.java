package com.inventory.pos.request;

import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInvoiceDetailRequest {
    private InvoiceDetailItem invoiceDetailItem;
    private Long invoiceId;
    private Long productId;
}
