package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Speciality;
import ng.com.smartcity.scbpetclinic.model.Vet;
import ng.com.smartcity.scbpetclinic.services.SpecialtyService;
import ng.com.smartcity.scbpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public Vet findById(Long id) {
        return map.get(id);
    }

    @Override
    public Vet save(Vet object) {
        if (object == null)
            throw new RuntimeException("Vet cannot be empty");
        if (object.getId() == null)
            object.setId(this.getNextId());
        if(object.getSpecialities().size() > 0){
            Stream<Speciality> specialityStream = object.getSpecialities().stream();
            specialityStream
                    .filter(speciality -> speciality.getId().equals(null))
                    .forEach(speciality -> speciality.setId(specialtyService.save(speciality).getId()));
        }

        return map.put(object.getId(), object);
    }

    @Override
    public void delete(Vet object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
