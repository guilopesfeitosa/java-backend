package com.back_end.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Schedule {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Time> times;

}
