package com.example.restservice;

import com.launchdarkly.client.LDClientInterface;
import com.launchdarkly.client.LDUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@ApiOperation(
			value = "Gets an invoice prepared with indicated invoiceId.",
			response = Invoice.class,
			produces = "application/json"
	)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping("/invoice")
	public Invoice tag(@RequestParam(value = "invoiceId") String invoiceId) {

		LDUser user = new LDUser.Builder("UNIQUE IDENTIFIER")
				.firstName("Bob")
				.lastName("Loblaw")
				.customString("groups", Arrays.asList("beta_testers"))
				.build();

		boolean isDummyInvoicingEnabled = ldClient.boolVariation(
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
