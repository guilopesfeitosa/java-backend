package com.back_end.service.custom;

import com.back_end.domain.entities.Appointment;
import com.back_end.domain.entities.Patient;
import com.back_end.domain.entities.Schedule;
import com.back_end.domain.entities.Time;
import com.back_end.repository.CRUDRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AppointmentServiceTest {

    @Mock
    private CRUDRepository<Appointment, UUID> appointmentRepository;

    @Mock
    private TimeService timeService;

    @InjectMocks
    private AppointmentService appointmentService;

    private Appointment appointment;
    private Time time;
    private Schedule schedule;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        UUID timeId = UUID.randomUUID();
        UUID scheduleId = UUID.randomUUID();

        time = new Time();
        time.setId(timeId);
        time.setAvailable(true);

        schedule = new Schedule();
        schedule.setId(scheduleId);

        patient = new Patient();
        patient.setId(UUID.randomUUID());

        appointment = new Appointment();
        appointment.setId(UUID.randomUUID());
        appointment.setSchedule(schedule);
        appointment.setSelectedTime(time);
        appointment.setPatient(patient);
    }

    @Test
    public void testCreateAppointmentWithValidTime() {
        when(timeService.findById(time.getId())).thenReturn(time);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment createdAppointment = appointmentService.create(appointment);

        assertNotNull(createdAppointment);
        assertEquals(schedule, createdAppointment.getSchedule());
        assertEquals(time, createdAppointment.getSelectedTime());
        verify(timeService, times(1)).findById(time.getId());
        verify(timeService, times(1)).update(time.getId(), time);
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    public void testFindAllAppointments() {
        when(appointmentRepository.findAll()).thenReturn(List.of(appointment));

        var appointments = appointmentService.findAll();

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        assertEquals(1, appointments.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void testFindAppointmentById() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointment));

        Appointment foundAppointment = appointmentService.findById(appointment.getId());

        assertNotNull(foundAppointment);
        assertEquals(appointment.getId(), foundAppointment.getId());
        verify(appointmentRepository, times(1)).findById(appointment.getId());
    }

    @Test
    public void testFindAppointmentByIdNotFound() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> appointmentService.findById(appointment.getId()));
        verify(appointmentRepository, times(1)).findById(appointment.getId());
    }

    @Test
    public void testDeleteAppointment() {
        doNothing().when(appointmentRepository).deleteById(appointment.getId());
        appointmentService.delete(appointment.getId());
        verify(appointmentRepository, times(1)).deleteById(appointment.getId());
    }
}