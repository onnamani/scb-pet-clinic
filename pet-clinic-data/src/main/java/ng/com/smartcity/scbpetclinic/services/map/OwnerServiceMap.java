package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
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

    @Override
    public Owner findByLastName(String LastName) {
        return map.entrySet()
                .stream().filter(entry -> entry.getValue().getLastName().equals(LastName))
                .collect(Collectors.toList())
                .get(0).getValue();
    }
}
