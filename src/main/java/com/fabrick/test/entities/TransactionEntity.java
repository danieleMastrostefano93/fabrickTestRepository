package com.fabrick.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "operation_id")
    private String operationId;

    @Column(name = "accounting_date")
    private Date accountingDate;

    @Column(name = "value_date")
    private Date valueDate;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

}
