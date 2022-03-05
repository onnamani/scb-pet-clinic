package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Vet;

import java.util.*;

public class VetServiceMap extends AbstractMapService<Vet, Long> {
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
