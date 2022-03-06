package ng.com.smartcity.scbpetclinic.services.map;

import java.util.*;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

}
