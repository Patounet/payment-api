package com.portfolio.paymentapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private Long transactionNumber;
    private Double amount;

    public Payment(@NotEmpty String firstName, @NotEmpty String lastName, Long transactionNumber, Double amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.transactionNumber = transactionNumber;
        this.amount=amount;
    }
}
