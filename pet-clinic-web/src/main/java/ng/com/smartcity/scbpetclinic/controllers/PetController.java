package ng.com.smartcity.scbpetclinic.controllers;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import ng.com.smartcity.scbpetclinic.services.PetService;
import ng.com.smartcity.scbpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    public static final String CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;

    public PetController(PetTypeService petTypeService, OwnerService ownerService, PetService petService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
       return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String getPetCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        pet.setOwner(owner);
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String postPetCreationForm(Owner owner, @Valid @ModelAttribute Pet pet, BindingResult result) {

        pet.setOwner(owner);

        if (pet.isNew() && owner.hasPet(pet) && StringUtils.hasLength(pet.getName()))
            result.rejectValue("name", "duplicate", "Pet already exists");

        owner.getPets().add(pet);

        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_PET_FORM;
        }

        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String getPetUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String postPetUpdateForm(@Valid @ModelAttribute Pet pet,
                                    BindingResult result,
                                    Owner owner,
                                    @PathVariable Long petId) {
        pet.setId(petId);
        pet.setOwner(owner);

        if (result.hasErrors())
            return CREATE_OR_UPDATE_PET_FORM;

        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
