package com.example.restservice;

import java.util.Date;



public class Invoice {

    private String invoiceId;
    private String invoice;

    public Invoice(String invoiceId, String invoice) {
        this.invoiceId = invoiceId;
        this.invoice = invoice;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
}
