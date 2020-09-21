package com.portfolio.paymentapi.repository;

import com.portfolio.paymentapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAll();

    List<Payment> findAllByTransactionNumber(Long transactionNumber);

    Optional<Payment> findOneByFirstNameAndLastName(String firstName, String lastName);

    List<Payment> findAllByAmount(Double amount);

}
