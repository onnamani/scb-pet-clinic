package ng.com.smartcity.scbpetclinic.services.map;

import ng.com.smartcity.scbpetclinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T, ID> implements CrudService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

}
