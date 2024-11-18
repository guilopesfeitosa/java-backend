package com.back_end.service.custom;

import com.back_end.domain.entities.Time;
import com.back_end.repository.CRUDRepository;
import com.back_end.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TimeService extends CRUDService<UUID, Time> {

    public TimeService(CRUDRepository<Time, UUID> repository) {
        super(repository);
    }
}
