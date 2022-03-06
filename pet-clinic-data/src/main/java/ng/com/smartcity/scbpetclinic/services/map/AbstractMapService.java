package ng.com.smartcity.scbpetclinic.services.map;

import java.util.*;

public abstract class AbstractMapService<T, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    protected Long getNextId(){
        Long nextId;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        } catch(NoSuchElementException e) {
            nextId = 1L;
        }
        return nextId;
    }

}
