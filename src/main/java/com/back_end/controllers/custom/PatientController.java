package com.back_end.controllers.custom;

import com.back_end.controllers.CRUDController;
import com.back_end.domain.entities.Patient;
import com.back_end.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("patients")
public class PatientController extends CRUDController<UUID, Patient> {

    public PatientController(CRUDService<UUID, Patient> service){
        super(service);
    }
}
