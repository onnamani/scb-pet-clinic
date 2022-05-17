package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import ng.com.smartcity.scbpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;

    public OwnerServiceMap(PetService petService) {
        this.petService = petService;
    }

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
        if (object == null)
            throw new RuntimeException("Owner cannot be empty");
        if (object.getId() == null)
            object.setId(this.getNextId());

        if(object.getPets() != null) {
            Stream<Pet> petStream = object.getPets().stream();
            petStream
                    .filter(pet -> pet.getId() == null)
                    .forEach(pet -> pet.setId(petService.save(pet).getId()));
        }
        map.put(object.getId(), object);
        return map.get(object.getId());
    }

    @Override
    public void delete(Owner owner) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(owner));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return map.values()
                .stream()
                .filter(owner -> !(owner.getLastName() == null))
                .filter(owner -> owner.getLastName().trim().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return null;
    }


}
