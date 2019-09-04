package com.gamelectronics.evaluateProject.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name="account_generator", sequenceName = "account_seq", allocationSize=50)
    private Long id;
    @Column(name = "account_uuid")
    private String uniqueId = UUID.randomUUID().toString();
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "card_number")
    private String cardNumber;
}