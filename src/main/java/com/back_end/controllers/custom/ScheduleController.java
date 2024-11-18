package com.back_end.controllers.custom;

import com.back_end.controllers.CRUDController;
import com.back_end.domain.entities.Schedule;
import com.back_end.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("schedules")
public class ScheduleController extends CRUDController<UUID, Schedule> {

    public ScheduleController(CRUDService<UUID, Schedule> service){
        super(service);
    }
}
