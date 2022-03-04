package ng.com.smartcity.scbpetclinic.services;

import ng.com.smartcity.scbpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
