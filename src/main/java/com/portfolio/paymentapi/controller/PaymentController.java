package com.portfolio.paymentapi.controller;

import com.portfolio.paymentapi.entity.Payment;
import com.portfolio.paymentapi.exceptions.ResourceNotFoundException;
import com.portfolio.paymentapi.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class PaymentController {


    PaymentService service;

    public PaymentController(PaymentService paymentService) {
        this.service = paymentService;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments() {
        return ResponseEntity.ok().body(service.getAllPayments());
    }

    @GetMapping("/paymentsByTransactionNumber")
    public ResponseEntity<List<Payment>> getPaymentsByTransactionNumber(@RequestParam Long transactionNumber) {
        return ResponseEntity.ok().body(service.findAllByTransactionNumber(transactionNumber));
    }


    @GetMapping("/paymentsByAmount")
    public ResponseEntity<List<Payment>> getPaymentsByAmount(@RequestParam Double amount) {
        return ResponseEntity.ok().body(service.findAllByAmount(amount));
    }

    @GetMapping("/paymentByFullName")
    public ResponseEntity<Payment> getPaymentByFullName(@RequestParam String firstName, @RequestParam String lastName) {

        Optional<Payment> payment = service.findByFirstNameAndLastName(firstName, lastName);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {

        Optional<Payment> payment = service.findPaymentById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {

        return ResponseEntity.ok().body(service.savePayment(payment));
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable long id, @RequestBody Payment payment) {
        payment.setId(id);
        return ResponseEntity.ok().body(service.updatePayment(payment));
    }

    @DeleteMapping("/payment/{id}")
    public HttpStatus deletePayment(@PathVariable("id") long id) {
        service.deleteById(id);
        return HttpStatus.OK;
    }


}
