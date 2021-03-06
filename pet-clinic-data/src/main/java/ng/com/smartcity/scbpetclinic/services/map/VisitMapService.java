package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.model.Visit;
import ng.com.smartcity.scbpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public Visit findById(Long id) {
        return map.get(id);
    }

    @Override
    public Visit save(Visit visit) {
        if(visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
         || visit.getPet().getOwner().getId() == null)
            throw new RuntimeException("Invalid visit");
        else if(visit.getId() == null)
            visit.setId(this.getNextId());
        map.put(visit.getId(), visit);
        return visit;
    }

    @Override
    public void delete(Visit visit) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(visit));
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
