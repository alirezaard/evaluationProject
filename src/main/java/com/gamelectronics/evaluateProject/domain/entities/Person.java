package com.gamelectronics.evaluateProject.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_person")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name="person_generator", sequenceName = "person_seq", allocationSize=50)
    private Long id;
    @Column(name = "person_uuid")
    private String uniqueId = UUID.randomUUID().toString();
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String mobile;
    private String phone;
}