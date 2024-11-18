package com.back_end.repository.custom;

import com.back_end.domain.entities.Appointment;
import com.back_end.repository.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentRespository extends CRUDRepository<Appointment, UUID> {
}
