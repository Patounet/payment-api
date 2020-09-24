package com.portfolio.paymentapi.service;

import com.portfolio.paymentapi.entity.Payment;


import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<Payment> getAllPayments();

    Optional<Payment> findPaymentById(Long id);

    List<Payment> findAllByTransactionNumber(Long number);

    Optional<Payment> findByFirstNameAndLastName(String firstName, String lastName);

    List<Payment> findAllByAmount(Double amount);

    Payment savePayment(Payment payment);

    void deleteById(long id);

    Payment updatePayment(Payment payment);
}
