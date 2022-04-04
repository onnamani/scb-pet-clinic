package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.services.PetService;
import ng.com.smartcity.scbpetclinic.services.PetTypeService;
import ng.com.smartcity.scbpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile({"default", "map"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

    private final VisitService visitService;
    private final PetTypeService petTypeService;

    public PetServiceMap(VisitService visitService, PetTypeService petTypeService) {
        this.visitService = visitService;
        this.petTypeService = petTypeService;
    }

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
        if (object == null)
            throw new RuntimeException("Pet cannot be empty");
        if(object.getId() == null)
            object.setId(this.getNextId());
        else if(object.getPetType() == null)
            throw new RuntimeException("Pet must have a pet type");
        else if(object.getPetType().getId() == null)
            object.getPetType().setId(petTypeService.save(object.getPetType()).getId());
        else if(object.getVisits().size() > 0)
            object.getVisits().stream()
                    .filter(visit -> visit.getId() == null)
                    .forEach(visit -> visit.setId(visitService.save(visit).getId()));

        map.put(object.getId(), object);
        return map.get(object.getId());
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
