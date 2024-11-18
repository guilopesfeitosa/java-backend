package com.back_end.service.custom;

import com.back_end.domain.entities.Schedule;
import com.back_end.domain.entities.Time;
import com.back_end.repository.CRUDRepository;
import com.back_end.repository.custom.ScheduleRepository;
import com.back_end.service.CRUDService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService extends CRUDService<UUID, Schedule> {

    private final TimeService timeService;
    private final DoctorService doctorService;

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(CRUDRepository<Schedule, UUID> repository,ScheduleRepository scheduleRepository, TimeService timeService, DoctorService doctorService) {
        super(repository);
        this.timeService = timeService;
        this.doctorService = doctorService;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule create(Schedule schedule) {

        schedule.setDoctor(this.doctorService.findById(schedule.getDoctor().getId()));

        Schedule createdSchedule = this.scheduleRepository.save(schedule);

        if(schedule.getTimes() != null && !schedule.getTimes().isEmpty()) {

            List<Time> scheduledTimes = schedule.getTimes().stream().map(time -> {
                time.setSchedule(createdSchedule.getId());
                return time;
            }).toList();

            List<Time> createdTimes = scheduledTimes.stream().map(time -> this.timeService.create(time)).toList();

            createdSchedule.setTimes(createdTimes);
        }

        return createdSchedule;
    }

}
