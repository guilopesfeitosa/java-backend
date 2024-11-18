package com.back_end.controllers.custom;

import com.back_end.controllers.CRUDController;
import com.back_end.domain.entities.Appointment;
import com.back_end.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("appointments")
public class AppointmentController extends CRUDController<UUID, Appointment> {

    public AppointmentController(CRUDService<UUID, Appointment> service){
        super(service);
    }

}
