package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

    PetServiceMap petServiceMap;
    Pet pet1;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap(new VisitMapService(), new PetTypeMapService());
        pet1 = Pet.builder().id(1L).petType(new PetType("Dog")).build();
        Pet pet2 = Pet.builder().id(2L).petType(new PetType("Cat")).build();
        petServiceMap.save(pet1);
        petServiceMap.save(pet2);
    }

    @Test
    void findAll() {
       Set<Pet> pets = petServiceMap.findAll();

       assertEquals(pets.size(), 2);
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(1L);
        Pet petNull = petServiceMap.findById(4L);

        assertEquals(pet, pet1);
        assertNull(petNull);
    }

    @Test
    void save() {
        Pet pet = Pet.builder().id(3L).petType(new PetType("Rabbit")).build();
        Pet petWithNoId = Pet.builder().petType(new PetType("Rabbit")).build();
        Pet savedPet = petServiceMap.save(pet);
        Pet savedPetWithNoId = petServiceMap.save(petWithNoId);

        assertEquals(petServiceMap.findAll().size(), 4);
        assertEquals(savedPet.getId(), 3L);
        assertNotNull(savedPetWithNoId.getId());
    }

    @Test
    void delete() {
        petServiceMap.delete(pet1);

        assertEquals(petServiceMap.findAll().size(), 1);
        assertNull(petServiceMap.findById(1L));
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(1L);

        assertEquals(petServiceMap.findAll().size(), 1);
        assertNull((petServiceMap.findById(1L)));
    }
}