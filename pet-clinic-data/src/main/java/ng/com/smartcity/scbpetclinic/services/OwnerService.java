package ng.com.smartcity.scbpetclinic.services;

import ng.com.smartcity.scbpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String LastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
