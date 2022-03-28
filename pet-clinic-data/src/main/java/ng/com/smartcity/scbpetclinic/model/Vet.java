package ng.com.smartcity.scbpetclinic.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities",
    joinColumns = @JoinColumn(name = "vet_id"),
    inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Speciality> specialties = new HashSet<>();

    public Set<Speciality> getSpecialties() {
        return specialties;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialties = specialities;
    }
}
