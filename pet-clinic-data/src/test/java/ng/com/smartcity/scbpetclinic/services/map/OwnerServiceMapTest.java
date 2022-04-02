package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {


    private PetService petService;
    private OwnerServiceMap ownerServiceMap;
    private final Long ownerId = 2L;
    private final Owner owner = Owner.builder().id(4L).firstName("Obinna").lastName("Nnamani").build();

    @BeforeEach
    void setUp() {
        petService = new PetServiceMap(new VisitMapService());
        ownerServiceMap = new OwnerServiceMap(petService);
        ownerServiceMap.save(this.owner);
        ownerServiceMap.save(Owner.builder().id(2L).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(owners.size(), 2);
    }

    @Test
    void findById() {
        assertEquals(ownerServiceMap.findById(ownerId).getId(), 2L);
    }

    @Test
    void save() {
        Owner ownerWithID = Owner.builder().id(1L).build();
        Owner ownerNoID = Owner.builder().build();
        assertEquals(ownerServiceMap.save(ownerWithID).getId(), 1L);
        assertTrue(ownerServiceMap.save(ownerNoID).getId() instanceof Long);
    }

    @Test
    void delete() {
        ownerServiceMap.delete(this.owner);
        assertFalse(ownerServiceMap.findAll().contains(this.owner));
        assertEquals(ownerServiceMap.findAll().size(), 1);
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(4L);
        assertEquals(ownerServiceMap.findById(4L), null);
        assertEquals(ownerServiceMap.findAll().size(), 1);
    }

    @Test
    void findByLastName() {
        assertEquals(ownerServiceMap.findByLastName("Nnamani"), this.owner);
        assertNull(ownerServiceMap.findByLastName("not there"));
    }
}