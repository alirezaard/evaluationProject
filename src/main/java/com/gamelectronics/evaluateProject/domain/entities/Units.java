package com.gamelectronics.evaluateProject.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_units")
public class Units {
    @Id
    @Column(name = "unit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_generator")
    @SequenceGenerator(name="unit_generator", sequenceName = "unit_seq", allocationSize=50)
    private Long id;
    @Column(name = "units_uuid")
    private String uniqueId = UUID.randomUUID().toString();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person owner;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person tenant;
    private String location;
    private Long balance;
    private Long area;
    @Column(name = "number_of_people")
    private int numOfPeople;
    @Column(name = "charge_amount")
    private Long chargeAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "building_id", referencedColumnName = "building_id")
    private Building building;
}