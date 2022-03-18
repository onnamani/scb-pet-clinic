package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public PetType findById(Long id) {
        return map.get(id);
    }

    @Override
    public PetType save(PetType object) {
        if (object != null && object.getId() == null)
            object.setId(this.getNextId());
        else throw new RuntimeException("PetType cannot be empty");
        return map.put(object.getId(), object);
    }

    @Override
    public void delete(PetType object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
