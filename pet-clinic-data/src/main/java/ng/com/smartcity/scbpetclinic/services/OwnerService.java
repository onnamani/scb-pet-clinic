package ng.com.smartcity.scbpetclinic.services;

import ng.com.smartcity.scbpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String LastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
