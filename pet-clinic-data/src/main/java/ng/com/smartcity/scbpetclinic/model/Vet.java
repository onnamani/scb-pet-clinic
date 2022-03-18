package ng.com.smartcity.scbpetclinic.model;

import java.util.*;

public class Vet extends Person {

    private Set<Speciality> specialties = new HashSet<>();

    public Set<Speciality> getSpecialties() {
        return specialties;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialties = specialities;
    }
}
