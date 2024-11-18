package com.back_end.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name= "name")
    private String name;

    @Column(name="document",unique = true)
    private String document;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name="phone")
    private String phone;

    @Column(name="age")
    private String age;

    @Column(name="gender")
    private String gender;

}
