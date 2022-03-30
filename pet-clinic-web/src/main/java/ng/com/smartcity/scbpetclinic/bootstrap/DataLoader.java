package ng.com.smartcity.scbpetclinic.bootstrap;

import ng.com.smartcity.scbpetclinic.model.*;
import ng.com.smartcity.scbpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(petTypeService.findAll().size() == 0)
            loadData();
    }


    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentisry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Obinna");
        owner1.setLastName("Nnamani");
        owner1.setAddress("17 Grace Crescent close, RD Road");
        owner1.setCity("Port Harcourt");
        owner1.setTelephone("08034444444");

        Pet owner1Pet = new Pet();
        owner1Pet.setName("Captain");
        owner1Pet.setPetType(savedDog);
        owner1Pet.setBirthDate(LocalDate.now());
        owner1Pet.setOwner(owner1);

        Visit owner1PetVisit = new Visit();
        owner1PetVisit.setPet(owner1Pet);
        owner1PetVisit.setDate(LocalDate.now());
        owner1PetVisit.setDescription("Sneezy Captain");
        owner1Pet.getVisits().add(owner1PetVisit);

        owner1.getPets().add(owner1Pet);

        ownerService.save(owner1);



        Owner owner2 = new Owner();
        owner2.setFirstName("Samuel");
        owner2.setLastName("Nnamani");
        owner2.setAddress("7 Nanka Street, New Haven");
        owner2.setCity("Enugu");
        owner2.setTelephone("08153333333");

        Pet owner2Pet = new Pet();
        owner2Pet.setName("Sandy");
        owner2Pet.setPetType(savedCat);
        owner2Pet.setOwner(owner2);
        owner2Pet.setBirthDate(LocalDate.now());

        System.out.println("Loaded Owners...");

        owner2.getPets().add(owner2Pet);

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Ogochukwu");
        vet1.setLastName("Nnamani");
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kene");
        vet2.setLastName("Nnamani");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
