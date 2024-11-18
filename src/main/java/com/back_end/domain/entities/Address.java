package com.back_end.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="cep",nullable = false)
    private Integer cep;

    @Column(name="street",nullable = false)
    private String street;

    @Column(name="number",nullable = false)
    private String number;

    @Column(name="neighborhood",nullable = false)
    private String neighborhood;

    @Column(name="complement",nullable = true)
    private String complement;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="state",nullable = false)
    private String state;

    @OneToMany()
    private List<Patient> patient;

}
