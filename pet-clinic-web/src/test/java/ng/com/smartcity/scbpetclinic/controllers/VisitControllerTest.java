package ng.com.smartcity.scbpetclinic.controllers;

import ng.com.smartcity.scbpetclinic.model.Pet;
import ng.com.smartcity.scbpetclinic.model.Visit;
import ng.com.smartcity.scbpetclinic.services.PetService;
import ng.com.smartcity.scbpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;
    Pet pet;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        pet = Pet.builder().id(1L).build();
    }

    @Test
    void getUpdateNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet", "visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void postUpdateNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/1/visits/new")
                        .param("date", "2022-05-19")
                        .param("description", "Fever"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("pet", "visit"))
                .andExpect(view().name("redirect:/owners/1"));

        verify(visitService, times(1)).save(any(Visit.class));
    }
}
