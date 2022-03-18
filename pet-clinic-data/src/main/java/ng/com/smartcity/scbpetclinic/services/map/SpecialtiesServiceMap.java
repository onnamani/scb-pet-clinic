package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Speciality;
import ng.com.smartcity.scbpetclinic.services.SpecialtiesService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecialtiesServiceMap extends AbstractMapService<Speciality, Long> implements SpecialtiesService {
    @Override
    public Set<Speciality> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public Speciality findById(Long id) {
        return map.get(id);
    }

    @Override
    public Speciality save(Speciality object) {
        if(object == null)
            throw new RuntimeException("Speciality cannot be empty");
        if(object.getId() == null)
            object.setId(this.getNextId());

        return map.put(object.getId(), object);
    }

    @Override
    public void delete(Speciality object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
