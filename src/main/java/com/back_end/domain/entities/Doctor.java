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
public class Doctor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "crmNumber",unique = true)
    private String crmNumber;

    @Column(name = "state")
    private String state;

}
