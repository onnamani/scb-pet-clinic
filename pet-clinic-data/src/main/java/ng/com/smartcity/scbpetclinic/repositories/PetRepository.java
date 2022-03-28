package ng.com.smartcity.scbpetclinic.repositories;

import ng.com.smartcity.scbpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
