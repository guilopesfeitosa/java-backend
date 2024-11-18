package com.back_end.repository.custom;

import com.back_end.domain.entities.Patient;
import com.back_end.repository.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends CRUDRepository<Patient, UUID> {
}
