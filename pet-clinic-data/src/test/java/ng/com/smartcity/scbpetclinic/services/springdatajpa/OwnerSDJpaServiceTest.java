package ng.com.smartcity.scbpetclinic.services.springdatajpa;

import ng.com.smartcity.scbpetclinic.model.Owner;
import ng.com.smartcity.scbpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private static final String LAST_NAME = "Nnamani";
    private Owner returnedOwner;
    private Set<Owner> ownerSet;
    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        ownerSet = new HashSet<>();
        ownerSet.add(returnedOwner);
        ownerSet.add(Owner.builder().id(2L).build());
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> returnedOwnerSet = ownerSDJpaService.findAll();

        assertEquals(returnedOwnerSet, ownerSet);
        assertEquals(returnedOwnerSet.size(), 2);
        assertNotNull(returnedOwnerSet);
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(returnedOwner));
        when(ownerRepository.findById(6L)).thenReturn(Optional.empty());

        assertEquals(ownerSDJpaService.findById(1L), returnedOwner);
        assertEquals(ownerSDJpaService.findById(1L).getId(), 1L);
        assertNull(ownerSDJpaService.findById(6L));
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner savedOwner = ownerSDJpaService.save(Owner.builder().id(4L).build());

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnedOwner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        assertEquals(ownerSDJpaService.findByLastName(LAST_NAME), returnedOwner);
        assertEquals(ownerRepository.findByLastName(LAST_NAME).getLastName(), LAST_NAME);
    }
}