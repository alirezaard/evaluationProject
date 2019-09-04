package com.gamelectronics.evaluateProject.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_address")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    @SequenceGenerator(name="address_generator", sequenceName = "address_seq", allocationSize=50)
    private Long id;
    @Column(name = "address_uuid")
    private String uniqueId = UUID.randomUUID().toString();
    private String state;
    private String city;
    private String district;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "address_detail")
    private String addressDetail;
}
