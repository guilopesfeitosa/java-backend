package com.back_end.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Appointment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Schedule schedule;

    @OneToOne()
    private Time selectedTime;

    @ManyToOne()
    private Patient patient;

}
