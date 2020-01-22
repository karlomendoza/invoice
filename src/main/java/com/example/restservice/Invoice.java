package com.example.restservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    private String invoiceId;
    private String issuingCompany;
    private String receiverName;
    private BigDecimal amount;
    private BigDecimal taxAmount;
    private String purpose;

}
