package com.back_end.service.custom;

import com.back_end.domain.entities.Appointment;
import com.back_end.domain.entities.Time;
import com.back_end.repository.CRUDRepository;
import com.back_end.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentService extends CRUDService<UUID, Appointment> {

    private final TimeService timeService;

    public AppointmentService(CRUDRepository<Appointment, UUID> repository, TimeService timeService) {
        super(repository);
        this.timeService = timeService;
    }

    @Override
    public Appointment create(Appointment appointment) {
        try {
            Time timeInDb = this.timeService.findById(appointment.getSelectedTime().getId());

            if (timeInDb.isAvailable() == false) {
                throw new RuntimeException("Invalid time");
            }

            if (appointment.getSchedule() == null) {
                throw new RuntimeException("Invalid schedule");
            }

            Appointment createdAppointment = super.create(appointment);

            timeInDb.setAvailable(false);

            this.timeService.update(timeInDb.getId(), timeInDb);

            return createdAppointment;
        } catch (RuntimeException e) {

            if(e.getMessage() != "Invalid time") {
                appointment.getSelectedTime().setAvailable(true);
                this.timeService.update(appointment.getSelectedTime().getId(), appointment.getSelectedTime());
            }

            throw new RuntimeException(e);
        }
    }
}
