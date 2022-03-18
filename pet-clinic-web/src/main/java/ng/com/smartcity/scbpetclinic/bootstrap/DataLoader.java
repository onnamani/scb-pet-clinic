package ng.com.smartcity.scbpetclinic.bootstrap;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.model.Vet;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import ng.com.smartcity.scbpetclinic.services.PetService;
import ng.com.smartcity.scbpetclinic.services.PetTypeService;
import ng.com.smartcity.scbpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
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
        owner1.setAddress("17 Grace Crescent close, RD Road");
        owner1.setCity("Port Harcourt");
        owner1.setTelephone("08034444444");
        Owner savedOwner1 = ownerService.save(owner1);

        Pet owner1Pet = new Pet();
        owner1Pet.setName("Captain");
        owner1Pet.setPetType(savedDogPetType);
        owner1Pet.setOwner(savedOwner1);
        owner1Pet.setBirthDate(LocalDate.now());
        Pet savedOwner1Pet = petService.save(owner1Pet);
        owner1.getPets().add(savedOwner1Pet);



        Owner owner2 = new Owner();
        owner2.setFirstName("Samuel");
        owner2.setLastName("Nnamani");
        owner2.setAddress("7 Nanka Street, New Haven");
        owner2.setCity("Enugu");
        owner2.setTelephone("08153333333");
        Owner savedOwner2 = ownerService.save(owner2);

        Pet owner2Pet = new Pet();
        owner2Pet.setName("Sandy");
        owner2Pet.setPetType(savedCatPetType);
        owner2Pet.setOwner(savedOwner2);
        owner2Pet.setBirthDate(LocalDate.now());
        Pet savedOwner2Pet = petService.save(owner2Pet);
        owner2.getPets().add(savedOwner2Pet);

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
