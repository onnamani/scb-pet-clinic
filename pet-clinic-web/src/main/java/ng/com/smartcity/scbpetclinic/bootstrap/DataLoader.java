package ng.com.smartcity.scbpetclinic.bootstrap;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.model.Vet;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import ng.com.smartcity.scbpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Obinna");
        owner1.setLastName("Nnamani");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Samuel");
        owner2.setLastName("Nnamani");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Ogochukwu");
        vet1.setLastName("Nnamani");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Kene");
        vet2.setLastName("Nnamani");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
