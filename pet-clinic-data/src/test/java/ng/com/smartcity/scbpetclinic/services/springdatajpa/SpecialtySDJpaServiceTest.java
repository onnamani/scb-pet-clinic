package ng.com.smartcity.scbpetclinic.services.springdatajpa;

import ng.com.smartcity.scbpetclinic.model.Speciality;
import ng.com.smartcity.scbpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialtySDJpaServiceTest {

    @InjectMocks
    SpecialtySDJpaService specialtySDJpaService;
    @Mock
    SpecialtyRepository specialtyRepository;

    Set<Speciality> specialities;
    Speciality specialityRadiology;
    Long radiologyId;

    @BeforeEach
    void setUp() {
        radiologyId = 1L;
        specialityRadiology = new Speciality();
        specialityRadiology.setId(radiologyId);
        specialityRadiology.setDescription("Radiology");

        Speciality specialitySurgery = new Speciality();
        specialitySurgery.setId(2L);
        specialitySurgery.setDescription("Surgery");

        specialities = new HashSet<>();
        specialities.add(specialityRadiology);
        specialities.add(specialitySurgery);
    }

    @Test
    void findAll() {
        when(specialtyRepository.findAll()).thenReturn(specialities);

        assertEquals(specialtySDJpaService.findAll().size(), 2);
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(specialityRadiology));

        assertEquals(specialtySDJpaService.findById(radiologyId), specialityRadiology);
        verify(specialtyRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(specialtyRepository.save(any())).thenReturn(specialityRadiology);

        assertEquals(specialtySDJpaService.save(specialityRadiology), specialityRadiology);
        verify(specialtyRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        specialtySDJpaService.delete(specialityRadiology);

        verify(specialtyRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        specialtySDJpaService.deleteById(radiologyId);

        verify(specialtyRepository, times(1)).deleteById(anyLong());
    }
}