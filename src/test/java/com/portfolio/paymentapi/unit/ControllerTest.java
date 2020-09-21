package com.portfolio.paymentapi.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.paymentapi.controller.PaymentController;
import com.portfolio.paymentapi.entity.Payment;
import com.portfolio.paymentapi.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;



@WebMvcTest(PaymentController.class)
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService service;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Payment> paymentList;

    @BeforeEach
    void setUp() {

        this.paymentList = new ArrayList<>();
        this.paymentList.add(new Payment("Georges", "Washington", 11L,10.0));
        this.paymentList.add(new Payment("Bruce", "Wayne",12L, 10.0));
        this.paymentList.add(new Payment("Clark", "Lewis", 13L, 20.0));

    }

    @Test
    void shouldRetrieveAllPayments() throws Exception {

        given(service.getAllPayments()).willReturn(this.paymentList);

        this.mockMvc.perform(get("/api/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(paymentList.size())));
    }

    @Test
    void shouldCreateNewPayment() throws Exception {
        given(service.savePayment(any(Payment.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Payment payment = new Payment("Tom", "Jones", 22L,30.0);
        this.mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payment)))
                .andExpect(jsonPath("$.firstName", is(payment.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(payment.getLastName())))
                .andExpect(jsonPath("$.transactionNumber",is(payment.getTransactionNumber())))
                .andExpect(jsonPath("$.amount",is(payment.getAmount())));
    }

   /* @Test
    void ShouldRetrievePaymentById() throws Exception {

        Long paymentId = 5L;
        Payment payment = new Payment("Karl", "Lewis", 110l,100.0);
        given(service.findPaymentById(paymentId)).willReturn(Optional.of(payment));

        this.mockMvc.perform(get("/api/payments/{id}", paymentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(payment.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(payment.getLastName())))
                .andExpect(jsonPath("$.transactionNumber",is(payment.getTransactionNumber())))
                .andExpect(jsonPath("$.amount",is(payment.getAmount())));

    */

   /* @Test
    void shouldReturn404WhenReturningNonExistingPayment() throws Exception {

        Long paymentId = 6L;
        given(service.findPaymentById(paymentId)).willReturn(Optional.empty());

        this.mockMvc.perform(get("/api/payments/{id}", paymentId))
                .andExpect(status().isNotFound());
    }
    */


    /*@Test
    void shouldUpdatePayment() throws Exception {
        Long paymentId = 12L;
        Payment payment = new Payment("Georges", "Washington", 11l,10.0);

        given(repository.findById(paymentId)).willReturn(Optional.of(payment));
        given(repository.save(any(Payment.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/payments/{id}", payment.getId())

    }

     */



}

