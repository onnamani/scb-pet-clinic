package ng.com.smartcity.scbpetclinic.model;

import java.util.*;

public class Owner extends Person {

    private Set<Pet> pets;

    public Set getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
