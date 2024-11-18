package com.back_end.service.custom;

import com.back_end.domain.entities.Address;
import com.back_end.domain.entities.Patient;
import com.back_end.repository.CRUDRepository;
import com.back_end.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService extends CRUDService<UUID, Patient> {

    private final AddressService addressService;

    public PatientService(CRUDRepository<Patient, UUID> repository, AddressService addressService) {
        super(repository);
        this.addressService = addressService;
    }

    @Override
    public Patient create(Patient patient) {

        if(patient.getAddress() != null) {
            Address address =this.addressService.create(patient.getAddress());
            patient.setAddress(address);
        }

        return super.create(patient);
    }

}
