package com.back_end.service.custom;
import com.back_end.domain.entities.Doctor;
import com.back_end.domain.entities.Schedule;
import com.back_end.domain.entities.Time;
import com.back_end.repository.CRUDRepository;
import com.back_end.repository.custom.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.hibernate.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ScheduleServiceTest {

    @Mock
    private CRUDRepository<Schedule, UUID> scheduleRepositoryMock;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private DoctorService doctorService;

    @Mock
    private TimeService timeService;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;
    private Doctor doctor;
    private Time time;
    private UUID scheduleId;

    @BeforeEach
    public void setUp() {
        scheduleId = UUID.randomUUID();
        doctor = new Doctor(UUID.randomUUID(), "Dr. Jane Smith", "123456", "SP");
        time = new Time();
        time.setId(UUID.randomUUID());
        time.setHour("08:00");

        schedule = new Schedule();
        schedule.setId(scheduleId);
        schedule.setDoctor(doctor);
        schedule.setDate(new Date());
        schedule.setTimes(new ArrayList<>(List.of(time)));
    }

    @Test
    public void testCreateScheduleWithTimes() {
        when(doctorService.findById(doctor.getId())).thenReturn(doctor);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);
        when(timeService.create(any(Time.class))).thenReturn(time);

        Schedule createdSchedule = scheduleService.create(schedule);

        assertNotNull(createdSchedule);
        assertEquals(doctor, createdSchedule.getDoctor());
        assertNotNull(createdSchedule.getTimes());
        assertEquals(1, createdSchedule.getTimes().size());
        verify(doctorService, times(1)).findById(doctor.getId());
        verify(scheduleRepository, times(1)).save(schedule);
        verify(timeService, times(1)).create(any(Time.class));
    }

    @Test
    public void testCreateScheduleWithoutTimes() {
        schedule.setTimes(null);
        when(doctorService.findById(doctor.getId())).thenReturn(doctor);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        Schedule createdSchedule = scheduleService.create(schedule);

        assertNotNull(createdSchedule);
        assertEquals(doctor, createdSchedule.getDoctor());
        assertNull(createdSchedule.getTimes());
        verify(doctorService, times(1)).findById(doctor.getId());
        verify(scheduleRepository, times(1)).save(schedule);
        verify(timeService, never()).create(any(Time.class));
    }

    @Test
    public void testFindAllSchedules() {
        when(scheduleRepositoryMock.findAll()).thenReturn(List.of(schedule));

        List<Schedule> schedules = scheduleService.findAll();

        assertNotNull(schedules);
        assertFalse(schedules.isEmpty());
        assertEquals(1, schedules.size());
        verify(scheduleRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindScheduleById() {
        when(scheduleRepositoryMock.findById(scheduleId)).thenReturn(Optional.of(schedule));

        Schedule foundSchedule = scheduleService.findById(scheduleId);

        assertNotNull(foundSchedule);
        assertEquals(scheduleId, foundSchedule.getId());
        verify(scheduleRepositoryMock, times(1)).findById(scheduleId);
    }

    @Test
    public void testFindScheduleByIdNotFound() {
        when(scheduleRepositoryMock.findById(scheduleId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> scheduleService.findById(scheduleId));
    }

}