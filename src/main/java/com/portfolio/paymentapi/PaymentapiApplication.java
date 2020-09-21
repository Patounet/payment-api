package com.portfolio.paymentapi;

import com.portfolio.paymentapi.entity.Payment;
import com.portfolio.paymentapi.service.PaymentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentapiApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoad(PaymentService service){
		return (args) -> {
			service.savePayment(new Payment("Georges", "Washington", 11l,10.0));
			service.savePayment(new Payment("Bruce", "Wayne",12l, 10.0));
			service.savePayment(new Payment("Clark", "Lewis", 13l, 20.0));
		};
	}
}
