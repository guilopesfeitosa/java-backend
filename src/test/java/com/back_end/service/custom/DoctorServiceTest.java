package com.back_end.service.custom;

import com.back_end.domain.entities.Doctor;
import com.back_end.repository.CRUDRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.hibernate.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DoctorServiceTest {

    @Mock
    private CRUDRepository<Doctor, UUID> doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;
    private UUID doctorId;

    @BeforeEach
    public void setUp() {
        doctorId = UUID.randomUUID();
        doctor = new Doctor();
        doctor.setId(doctorId);
        doctor.setName("Dr. John Doe");
        doctor.setCrmNumber("123456");
        doctor.setState("SP");
    }

    @Test
    public void testCreateDoctor() {
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Doctor createdDoctor = doctorService.create(doctor);

        assertNotNull(createdDoctor);
        assertEquals("Dr. John Doe", createdDoctor.getName());
        assertEquals("123456", createdDoctor.getCrmNumber());
        assertEquals("SP", createdDoctor.getState());
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testFindAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(List.of(doctor));

        List<Doctor> doctors = doctorService.findAll();

        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
        assertEquals(1, doctors.size());
        assertEquals("Dr. John Doe", doctors.get(0).getName());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    public void testFindDoctorById() {
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        Doctor foundDoctor = doctorService.findById(doctorId);

        assertNotNull(foundDoctor);
        assertEquals(doctorId, foundDoctor.getId());
        assertEquals("Dr. John Doe", foundDoctor.getName());
        verify(doctorRepository, times(1)).findById(doctorId);
    }

    @Test
    public void testFindDoctorByIdNotFound() {
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> doctorService.findById(doctorId));
    }

    @Test
    public void testUpdateDoctor() {
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        doctor.setName("Dr. Jane Doe");
        doctor.setCrmNumber("654321");
        doctor.setState("RJ");

        Doctor updatedDoctor = doctorService.update(doctorId, doctor);

        assertNotNull(updatedDoctor);
        assertEquals("Dr. Jane Doe", updatedDoctor.getName());
        assertEquals("654321", updatedDoctor.getCrmNumber());
        assertEquals("RJ", updatedDoctor.getState());
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testUpdateDoctorNotFound() {
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> doctorService.update(doctorId, doctor));
    }

    @Test
    public void testDeleteDoctor() {
        doNothing().when(doctorRepository).deleteById(doctorId);
        doctorService.delete(doctorId);
        verify(doctorRepository, times(1)).deleteById(doctorId);
    }
}