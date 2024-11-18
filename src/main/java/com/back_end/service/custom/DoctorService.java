package com.back_end.service.custom;

import com.back_end.domain.entities.Doctor;
import com.back_end.repository.CRUDRepository;
import com.back_end.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DoctorService extends CRUDService<UUID, Doctor> {

    public DoctorService(CRUDRepository<Doctor, UUID> repository) {
        super(repository);
    }
}
