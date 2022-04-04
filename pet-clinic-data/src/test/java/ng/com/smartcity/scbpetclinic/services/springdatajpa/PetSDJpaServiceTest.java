package ng.com.smartcity.scbpetclinic.services.springdatajpa;

import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.repositories.PetRepository;
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
class PetSDJpaServiceTest {

    @InjectMocks
    PetSDJpaService petSDJpaService;
    @Mock
    PetRepository petRepository;
    private Pet pet;
    private Set<Pet> pets;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).petType(new PetType("Cat")).build();
        pets = new HashSet<>();
        pets.add(pet);
        pets.add(Pet.builder().id(2L).petType((new PetType("Dog"))).build());
    }

    @Test
    void findAll() {
        when(petRepository.findAll()).thenReturn(pets);
        Set<Pet> returnedPets = petSDJpaService.findAll();

        assertEquals(returnedPets.size(), 2);
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.findById(4L)).thenReturn(Optional.empty());

        assertEquals(petSDJpaService.findById(1L), pet);
        assertNull(petSDJpaService.findById(4L));
    }

    @Test
    void save() {
        when(petRepository.save(any())).thenReturn(pet);

        assertEquals(petSDJpaService.save(pet), pet);
    }

    @Test
    void delete() {
        petSDJpaService.delete(pet);

        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petSDJpaService.deleteById(1L);

        verify(petRepository, times(1)).deleteById(anyLong());
    }
}