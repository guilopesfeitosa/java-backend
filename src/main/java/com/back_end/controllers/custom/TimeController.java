package com.back_end.controllers.custom;

import com.back_end.controllers.CRUDController;
import com.back_end.domain.entities.Time;
import com.back_end.service.CRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("time")
public class TimeController extends CRUDController<UUID, Time> {

    public TimeController(CRUDService<UUID, Time> service){
        super(service);
    }
}
