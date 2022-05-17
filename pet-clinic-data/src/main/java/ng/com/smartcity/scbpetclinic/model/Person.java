package ng.com.smartcity.scbpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Person(Long id, String firstName, String lastName) {
        super(id);

        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null) {
            this.firstName = firstName;
            return;
        }
        this.firstName = firstName.toUpperCase();
    }

    public void setLastName(String lastName) {
        if(lastName == null) {
            this.lastName = lastName;
            return;
        }

        this.lastName = lastName.toUpperCase();
    }

}
