package com.portfolio.paymentapi.service;

import com.portfolio.paymentapi.entity.Payment;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<Payment> getAllPayments();

    Payment findPaymentById(Long id);

    List<Payment> findAllByTransactionNumber(Long number);

    Payment findByFirstNameAndLastName(String firstName, String lastName);

    List<Payment> findAllByAmount(Double amount);

    Payment savePayment(Payment payment);

    void deleteById(long id);

    Payment updatePayment(Payment payment);
}
