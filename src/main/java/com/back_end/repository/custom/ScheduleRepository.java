package com.back_end.repository.custom;

import com.back_end.domain.entities.Schedule;
import com.back_end.repository.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends CRUDRepository<Schedule, UUID> {
}
