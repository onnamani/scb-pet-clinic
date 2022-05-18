package ng.com.smartcity.scbpetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    private String name;

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public boolean equals(Object petTypeObject) {
        PetType petType;
        if (petTypeObject == null)
            return false;
        if (petTypeObject instanceof PetType) {
            petType = (PetType) petTypeObject;
            return this.getId().equals(petType.getId());
        }
        return false;
    }

}
