package com.example.restservice;

import java.io.IOException;
import java.util.Arrays;


import com.launchdarkly.client.LDClient;
import com.launchdarkly.client.LDUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class InvoiceController {


	@GetMapping("/invoice")
	public Invoice tag(@RequestParam(value = "invoiceId") String invoiceId) throws IOException {


		LDClient ldClient = new LDClient("sdk-d3e4544e-0461-4340-86a2-77d6f70fbc06");
		LDUser user = new LDUser.Builder("UNIQUE IDENTIFIER")
				.firstName("Bob")
				.lastName("Loblaw")
				.customString("groups", Arrays.asList("beta_testers"))
				.build();

		boolean showFeature = ldClient.boolVariation("test-1", user, false);


		ldClient.close();

		if (showFeature) {
			return new Invoice( invoiceId, "456");
		} else {
			String invoice = "the invoice";
			return new Invoice( invoiceId, invoice);
		}

	}
}
