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
    private final PetService petService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      PetService petService,
                      SpecialtyService specialtyService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(petTypeService.findAll().size() == 0)
            loadData();
    }


    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);
//        System.out.println(savedRadiology);

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
        Owner savedOwner1 = ownerService.save(owner1);

        Pet owner1Pet = new Pet();
        owner1Pet.setName("Captain");
        owner1Pet.setPetType(savedDogPetType);
        owner1Pet.setOwner(savedOwner1);
        owner1Pet.setBirthDate(LocalDate.now());
        Pet savedOwner1Pet = petService.save(owner1Pet);
        owner1.getPets().add(savedOwner1Pet);

        Visit owner1PetVisit = new Visit();
        owner1PetVisit.setPet(savedOwner1Pet);
        owner1PetVisit.setDate(LocalDate.now());
        owner1PetVisit.setDescription("Sneezy Captain");

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
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kene");
        vet2.setLastName("Nnamani");
        vet2.getSpecialties().add(savedSurgery);
//        System.out.println(vet2.getSpecialties());
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
