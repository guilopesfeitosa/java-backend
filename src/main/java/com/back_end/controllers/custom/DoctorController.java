package com.back_end.controllers.custom;

import com.back_end.controllers.CRUDController;
import com.back_end.domain.entities.Doctor;
import com.back_end.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("doctors")
public class DoctorController extends CRUDController<UUID, Doctor> {

    public DoctorController(CRUDService<UUID, Doctor> service){
        super(service);
    }

}
