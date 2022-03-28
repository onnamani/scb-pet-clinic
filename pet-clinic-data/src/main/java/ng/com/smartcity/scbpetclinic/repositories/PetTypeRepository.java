package ng.com.smartcity.scbpetclinic.repositories;

import ng.com.smartcity.scbpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
