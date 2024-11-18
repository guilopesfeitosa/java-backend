package com.back_end.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Time {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "hour")
    private String hour;

    @Column(name = "isAvailable")
    private boolean isAvailable = true;

    @JoinColumn(name = "schedule_id")
    private UUID schedule;

    @OneToOne
    private Appointment appointment;

}
