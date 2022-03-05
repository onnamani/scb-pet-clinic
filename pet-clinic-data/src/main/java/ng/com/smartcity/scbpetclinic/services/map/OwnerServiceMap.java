package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Owner;

import java.util.*;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> {
    @Override
    public Set<Owner> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public Owner findById(Long id) {
        return map.get(id);
    }

    @Override
    public Owner save(Owner object) {
        return map.put(object.getId(), object);
    }

    @Override
    public void delete(Owner object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
