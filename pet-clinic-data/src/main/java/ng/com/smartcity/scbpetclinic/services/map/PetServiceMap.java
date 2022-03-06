package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.services.PetService;

import java.util.*;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public Pet findById(Long id) {
        return map.get(id);
    }

    @Override
    public Pet save(Pet object) {
        return map.put(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
