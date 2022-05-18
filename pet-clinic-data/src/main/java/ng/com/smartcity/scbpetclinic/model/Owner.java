package ng.com.smartcity.scbpetclinic.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if (pets != null)
            this.pets = pets;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public boolean hasPet(Pet newPet) {
        if (pets.size() == 0)
            return false;

        return pets.stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(newPet.getName()))
                .filter(pet -> pet.getPetType().equals(newPet.getPetType()))
                .anyMatch(pet -> pet instanceof Pet);
    }
}
