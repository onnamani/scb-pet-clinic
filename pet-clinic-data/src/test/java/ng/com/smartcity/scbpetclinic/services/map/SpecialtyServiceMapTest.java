package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyServiceMapTest {

    SpecialtyServiceMap specialtyServiceMap;
    Speciality specialityRadiology;
    Long radiologyId;

    @BeforeEach
    void setUp() {
        specialtyServiceMap = new SpecialtyServiceMap();

        radiologyId = specialtyServiceMap.getNextId();

        specialityRadiology = new Speciality();
        specialityRadiology.setId(radiologyId);
        specialityRadiology.setDescription("Radiology");
        specialtyServiceMap.map.put(specialityRadiology.getId(), specialityRadiology);

        Speciality specialitySurgery = new Speciality();
        specialitySurgery.setId(specialtyServiceMap.getNextId());
        specialitySurgery.setDescription("Surgery");
        specialtyServiceMap.map.put(specialitySurgery.getId(), specialitySurgery);

    }

    @Test
    void findAll() {
        assertEquals(specialtyServiceMap.findAll().size(), 2);
    }

    @Test
    void findById() {
        Speciality returnedSpecialty = specialtyServiceMap.findById(radiologyId);

        assertNotNull(returnedSpecialty);
        assertEquals(returnedSpecialty, specialityRadiology);
    }

    @Test
    void save() {
        Speciality specialty = new Speciality();
        specialty.setDescription("Cardiology");

        Speciality savedSpecialty = specialtyServiceMap.save(specialty);

        assertNotNull(savedSpecialty);
        assertNotNull(savedSpecialty.getId());
    }

    @Test
    void delete() {
        specialtyServiceMap.delete(specialityRadiology);

        assertEquals(specialtyServiceMap.map.size(), 1);
    }

    @Test
    void deleteById() {
        specialtyServiceMap.deleteById(radiologyId);

        assertEquals(specialtyServiceMap.map.size(), 1);
    }
}