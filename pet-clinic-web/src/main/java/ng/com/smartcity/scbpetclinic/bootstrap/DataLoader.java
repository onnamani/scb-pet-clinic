package ng.com.smartcity.scbpetclinic.bootstrap;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.model.Vet;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import ng.com.smartcity.scbpetclinic.services.PetTypeService;
import ng.com.smartcity.scbpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Obinna");
        owner1.setLastName("Nnamani");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Samuel");
        owner2.setLastName("Nnamani");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ogochukwu");
        vet1.setLastName("Nnamani");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kene");
        vet2.setLastName("Nnamani");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
