package ng.com.smartcity.scbpetclinic.controllers;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;
    @Mock
    private Model model;
    @InjectMocks
    private OwnerController ownerController;

    private Set<Owner> owners;
    private MockMvc mockMvc;
    private ArgumentCaptor<Set<Owner>> argumentCaptor;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        argumentCaptor = ArgumentCaptor.forClass(Set.class);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void listOwners() throws Exception {
        //when
        when(ownerService.findAll()).thenReturn(owners);
        String view = ownerController.listOwners(model);

        //then
        assertEquals(view, "owners/index");
        verify(model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().size(), 2);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index",
                        "/owners/", "/owners/", "/owners/index.html"))
                .andExpect(status().isOk());
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
    }
}