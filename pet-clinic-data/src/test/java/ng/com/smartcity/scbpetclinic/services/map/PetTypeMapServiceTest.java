package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    PetType petTypeCat;
    Long catId;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();

        petTypeCat = new PetType();
        petTypeCat.setId(petTypeMapService.getNextId());
        petTypeCat.setName("Cat");
        petTypeMapService.map.put(petTypeCat.getId(), petTypeCat);

        PetType petTypeDog = new PetType();
        petTypeDog.setId(petTypeMapService.getNextId());
        petTypeDog.setName("Dog");
        petTypeMapService.map.put(petTypeDog.getId(), petTypeDog);

        catId = petTypeCat.getId();
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeMapService.findAll();

        assertEquals(petTypes.size(), 2);
    }

    @Test
    void findById() {
        PetType returnedPetType = petTypeMapService.findById(catId);
        PetType returnedPetTypeInvlaidId = petTypeMapService.findById(8L);

        assertEquals(petTypeCat, returnedPetType);
        assertEquals(petTypeCat.getId(), catId);
        assertNull(returnedPetTypeInvlaidId);
    }

    @Test
    void save() {
        PetType petType = new PetType();
        petType.setName("Rabbit");

        PetType savedPetType = petTypeMapService.save(petType);

        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(savedPetType.getName(), "Rabbit");
        assertEquals(petTypeMapService.map.size(), 3);
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeCat);

        assertEquals(petTypeMapService.map.size(), 1);
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(catId);

        assertEquals(petTypeMapService.map.size(), 1);
    }
}