package com.back_end.service.custom;

import com.back_end.domain.entities.Address;
import com.back_end.domain.entities.Patient;
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
public class PatientServiceTest {

    @Mock
    private CRUDRepository<Patient, UUID> patientRepository;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private Address address;

    @BeforeEach
    public void setUp() {
        UUID patientId = UUID.randomUUID();
        UUID addressId = UUID.randomUUID();

        address = new Address();
        address.setId(addressId);
        address.setStreet("123 Main St");
        address.setCity("Sample City");
        address.setState("ST");

        patient = new Patient();
        patient.setId(patientId);
        patient.setName("John Doe");
        patient.setDocument("12345678900");
        patient.setPhone("555-1234");
        patient.setAge("30");
        patient.setGender("Male");
        patient.setAddress(address);
    }

    @Test
    public void testCreatePatientWithAddress() {
        when(addressService.create(any(Address.class))).thenReturn(address);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.create(patient);

        assertNotNull(createdPatient);
        assertEquals("John Doe", createdPatient.getName());
        assertEquals("12345678900", createdPatient.getDocument());
        assertEquals("555-1234", createdPatient.getPhone());
        assertEquals("30", createdPatient.getAge());
        assertEquals("Male", createdPatient.getGender());
        assertNotNull(createdPatient.getAddress());
        verify(addressService, times(1)).create(any(Address.class));
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testCreatePatientWithoutAddress() {
        patient.setAddress(null);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.create(patient);

        assertNotNull(createdPatient);
        assertNull(createdPatient.getAddress());
        verify(addressService, never()).create(any(Address.class));
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testFindPatientById() {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.findById(patient.getId());

        assertNotNull(foundPatient);
        assertEquals(patient.getId(), foundPatient.getId());
        assertEquals("John Doe", foundPatient.getName());
        assertEquals("555-1234", foundPatient.getPhone());
        verify(patientRepository, times(1)).findById(patient.getId());
    }

    @Test
    public void testFindPatientByIdNotFound() {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> patientService.findById(patient.getId()));
        verify(patientRepository, times(1)).findById(patient.getId());
    }

    @Test
    public void testUpdatePatient() {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        patient.setPhone("555-5678");
        patient.setGender("Female");

        Patient updatedPatient = patientService.update(patient.getId(), patient);

        assertNotNull(updatedPatient);
        assertEquals("555-5678", updatedPatient.getPhone());
        assertEquals("Female", updatedPatient.getGender());
        verify(patientRepository, times(1)).findById(patient.getId());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testDeletePatient() {
        doNothing().when(patientRepository).deleteById(patient.getId());

        patientService.delete(patient.getId());

        verify(patientRepository, times(1)).deleteById(patient.getId());
    }

    @Test
    public void testFindAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        var patients = patientService.findAll();

        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        assertEquals(1, patients.size());
        assertEquals("John Doe", patients.get(0).getName());
        verify(patientRepository, times(1)).findAll();
    }
}