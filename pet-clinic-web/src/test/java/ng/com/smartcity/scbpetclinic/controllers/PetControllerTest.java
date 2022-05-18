package ng.com.smartcity.scbpetclinic.controllers;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.model.PetType;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import ng.com.smartcity.scbpetclinic.services.PetService;
import ng.com.smartcity.scbpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    BindingResult result;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;
    Owner owner;
    Set<PetType> petTypes;
    Pet ownerPet;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();
        ownerPet = Pet.builder().id(2L).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void getPetCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner", "pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void postPetCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/new")
                        .param("name", "Captain"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void getPetEditForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(ownerPet);

        mockMvc.perform((MockMvcRequestBuilders.get("/owners/1/pets/2/edit")))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void postPetEditForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.save(any(Pet.class))).thenReturn(ownerPet);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/edit")
                        .param("name", "Captain"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).save(any(Pet.class));
    }
}
