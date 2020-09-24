package com.portfolio.paymentapi.service;

import com.portfolio.paymentapi.entity.Payment;
import com.portfolio.paymentapi.exceptions.ResourceNotFoundException;
import com.portfolio.paymentapi.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {


    PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    @Override
    public Optional<Payment> findPaymentById(Long id) {

        return repository.findById(id);
    }


    @Override
    public List<Payment> findAllByTransactionNumber(Long number) {
        return repository.findAllByTransactionNumber(number);
    }

    @Override
    public Optional<Payment> findByFirstNameAndLastName(String firstName, String lastName) {
        return  repository.findOneByFirstNameAndLastName(firstName, lastName);

    }

    @Override
    public List<Payment> findAllByAmount(Double amount) {
        return repository.findAllByAmount(amount);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        Optional<Payment> _payment = repository.findById(payment.getId());

        if (_payment.isPresent()) {
            Payment paymentData = _payment.get();
            paymentData.setFirstName(payment.getFirstName());
            paymentData.setLastName(payment.getLastName());
            paymentData.setTransactionNumber(payment.getTransactionNumber());
            paymentData.setAmount(payment.getAmount());
            return paymentData;
        } else {
            throw new ResourceNotFoundException("Payment not found:" + payment.getId());
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<Payment> payment = repository.findById(id);

        if (payment.isPresent()) {
            repository.delete(payment.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
}
