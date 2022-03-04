package ng.com.smartcity.scbpetclinic.services;

import ng.com.smartcity.scbpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
