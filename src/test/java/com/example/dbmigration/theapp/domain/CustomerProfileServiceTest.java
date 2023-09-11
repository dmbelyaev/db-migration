package com.example.dbmigration.theapp.domain;

import com.example.dbmigration.theapp.data.AddressEntity;
import com.example.dbmigration.theapp.data.CustomerProfileEntity;
import com.example.dbmigration.theapp.data.CustomerProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerProfileServiceTest {

    @Mock
    private CustomerProfileRepository repository;

    @InjectMocks
    private CustomerProfileService subject;

    @Test
    void shouldDelegateToRepositoryToPersistProfile() {
        // given
        var customerProfile = new CustomerProfileCreateRequest("Joe", "Doe", "joe.doe@test.org",
                new Address("street", "city", "00000"));

        when(repository.save(any())).thenAnswer((invocation) -> invocation.getArgument(0));

        // when
        var result = subject.create(customerProfile);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo(customerProfile.getFirstName());
        assertThat(result.getLastName()).isEqualTo(customerProfile.getLastName());
        assertThat(result.getEmail()).isEqualTo(customerProfile.getEmail());
        assertThat(result.getId()).isNotBlank();
        assertThat(result.getAddresses()).hasSize(1);

        var customerProfileEntityCaptor = ArgumentCaptor.forClass(CustomerProfileEntity.class);
        verify(repository).save(customerProfileEntityCaptor.capture());

        var customerProfileEntity = customerProfileEntityCaptor.getValue();
        assertThat(customerProfileEntity.getFirstName()).isEqualTo(customerProfile.getFirstName());
        assertThat(customerProfileEntity.getLastName()).isEqualTo(customerProfile.getLastName());
        assertThat(customerProfileEntity.getEmail()).isEqualTo(customerProfile.getEmail());
        assertThat(customerProfileEntity.getId()).isEqualTo(result.getId());
        assertThat(customerProfileEntity.getAddresses()).hasSize(1);
        assertThat(customerProfileEntity.getAddresses().get(0).isMain()).isTrue();
        assertThat(customerProfileEntity.getAddresses().get(0).getCity()).isEqualTo("city");
        assertThat(customerProfileEntity.getAddresses().get(0).getStreet()).isEqualTo("street");
        assertThat(customerProfileEntity.getAddresses().get(0).getZipCode()).isEqualTo("00000");
    }

    @Test
    void shouldDelegateToRepositoryToRetrieveProfile() {

        var id = UUID.randomUUID().toString();
        var entity = new CustomerProfileEntity().setId(id).setFirstName("Joe").setLastName("Doe").setEmail("joe.doe@test.org");
        when(repository.findById(any())).thenReturn(Optional.of(entity));

        // when
        var resultOptional = subject.getById(id);

        // then
        assertThat(resultOptional).isPresent();

        var result = resultOptional.get();
        assertThat(result.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(result.getLastName()).isEqualTo(entity.getLastName());
        assertThat(result.getEmail()).isEqualTo(entity.getEmail());
        assertThat(result.getId()).isEqualTo(id);

        verify(repository).findById(id);
    }

    @Test
    void changeShouldUpdateNames() {
        // given
        var customerProfileChangeRequest = new CustomerProfileChangeRequest("John", "Does");

        var id = UUID.randomUUID().toString();
        var entity = new CustomerProfileEntity().setId(id).setFirstName("Joe").setLastName("Doe").setEmail("joe.doe@test.org");
        when(repository.findById(any())).thenReturn(Optional.of(entity));

        // when
        subject.change(id, customerProfileChangeRequest);

        // then
        var entityCaptor = ArgumentCaptor.forClass(CustomerProfileEntity.class);
        verify(repository).save(entityCaptor.capture());

        var profile = entityCaptor.getValue();
        assertThat(profile.getFirstName()).isEqualTo("John");
        assertThat(profile.getLastName()).isEqualTo("Does");
    }

    @Test
    void deleteShouldRemoveFromRepository() {
        // given
        var id = UUID.randomUUID().toString();

        // when
        subject.delete(id);

        // then
        verify(repository).deleteById(id);
    }

    @Test
    void getAllShouldDelegateToRepositoryToRetrieveProfile() {

        var id = UUID.randomUUID().toString();
        var entity = new CustomerProfileEntity()
                .setId(id).setFirstName("Joe")
                .setLastName("Doe")
                .setEmail("joe.doe@test.org")
                .setAddresses(List.of(new AddressEntity().setStreet("street").setCity("city").setZipCode("00000").setMain(false)));
        when(repository.streamAll()).thenReturn(Stream.of(entity));

        // when
        var result = subject.getAll();

        // then
        verify(repository).streamAll();

        var listResult = result.collect(Collectors.toList());
        assertThat(listResult).hasSize(1);

        var firstResponse = listResult.get(0);
        assertThat(firstResponse.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(firstResponse.getLastName()).isEqualTo(entity.getLastName());
        assertThat(firstResponse.getEmail()).isEqualTo(entity.getEmail());
        assertThat(firstResponse.getId()).isEqualTo(id);
        assertThat(firstResponse.getAddresses()).hasSize(1);
        assertThat(firstResponse.getAddresses().get(0).getStreet()).isEqualTo("street");
        assertThat(firstResponse.getAddresses().get(0).getCity()).isEqualTo("city");
        assertThat(firstResponse.getAddresses().get(0).getZipCode()).isEqualTo("00000");
        assertThat(firstResponse.getAddresses().get(0).isMain()).isFalse();
    }
}
