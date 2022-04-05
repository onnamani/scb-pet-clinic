package ng.com.smartcity.scbpetclinic.services.springdatajpa;

import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @InjectMocks
    PetTypeSDJpaService petTypeSDJpaService;
    @Mock
    PetTypeRepository petTypeRepository;

    PetType petTypeCat;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        petTypeCat = new PetType();
        petTypeCat.setId(1L);
        petTypeCat.setName("Cat");

        PetType petTypeDog = new PetType();
        petTypeDog.setId(2L);
        petTypeDog.setName("Dog");

        petTypes = new HashSet<>();
        petTypes.add(petTypeCat);
        petTypes.add(petTypeDog);
    }

    @Test
    void findAll() {
        when(petTypeRepository.findAll()).thenReturn(petTypes);

        assertEquals(petTypeSDJpaService.findAll().size(), 2);
        verify(petTypeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(1L)).thenReturn(Optional.of(petTypeCat));

        assertEquals(petTypeSDJpaService.findById(1L), petTypeCat);
        verify(petTypeRepository, times(1)).findById(anyLong());

    }

    @Test
    void save() {
        when(petTypeRepository.save(any())).thenReturn(petTypeCat);

        assertNotNull(petTypeSDJpaService.save(petTypeCat));
        verify(petTypeRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        petTypeSDJpaService.delete(petTypeCat);

        verify(petTypeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petTypeSDJpaService.deleteById(1L);

        verify(petTypeRepository, times(1)).deleteById(anyLong());
    }
}