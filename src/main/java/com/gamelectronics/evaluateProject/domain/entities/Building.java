package com.gamelectronics.evaluateProject.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_building")
public class Building {
    @Id
    @Column(name = "building_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "build_generator")
    @SequenceGenerator(name="build_generator", sequenceName = "build_seq", allocationSize=50)
    private Long id;
    @Column(name = "building_uuid")
    private String uniqueId = UUID.randomUUID().toString();
    private Long balance;

    @Column(name = "Number_of_unit")
    private int unitsNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person manager;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Units> units = new ArrayList<>();
}