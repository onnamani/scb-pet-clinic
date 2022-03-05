package ng.com.smartcity.scbpetclinic.services;

import ng.com.smartcity.scbpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String LastName);
}
