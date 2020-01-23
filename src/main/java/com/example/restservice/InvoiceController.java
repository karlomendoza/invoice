package com.example.restservice;

import com.launchdarkly.client.LDClientInterface;
import com.launchdarkly.client.LDUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;


@Slf4j
@RestController
public class InvoiceController {

	private final LDClientInterface ldClient;

	public InvoiceController(LDClientInterface ldClient) {
		this.ldClient = ldClient;
	}

	@GetMapping("/invoice")
	public Invoice tag(@RequestParam(value = "invoiceId") String invoiceId) {

		LDUser user = new LDUser.Builder("UNIQUE IDENTIFIER")
				.firstName("Bob")
				.lastName("Loblaw")
				.customString("groups", Arrays.asList("beta_testers"))
				.build();

		boolean isDummyInvoicingEnabled = ldClient
				.boolVariation(
						"dummy-invoice-enabled",
						user,
						true
				);

		//ldClient.close();
		log.info(String.format("dummy-invoice-enabled flag contains: %b", isDummyInvoicingEnabled));
		if (isDummyInvoicingEnabled) {
			// return a dummy invoice
			return Invoice
					.builder()
					.amount(new BigDecimal(100.0D))
					.invoiceId(invoiceId)
					.issuingCompany("Fictitious Ltd.")
					.receiverName("John Doe")
					.taxAmount(new BigDecimal(10.0D))
					.purpose("Dummy invoice")
					.build();
		} else {
			return Invoice
					.builder()
					.amount(new BigDecimal(100_000_000.0D))
					.invoiceId(invoiceId)
					.issuingCompany("Digital OnUs Ltd.")
					.receiverName("HSBC bank")
					.taxAmount(new BigDecimal(16_000_000.0D))
					.purpose("Tax reporting")
					.build();
		}

	}

}
