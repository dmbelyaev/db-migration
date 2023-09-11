package com.example.dbmigration.theapp.domain;

import com.example.dbmigration.theapp.data.AddressEntity;
import com.example.dbmigration.theapp.data.CustomerProfileEntity;
import com.example.dbmigration.theapp.data.CustomerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class CustomerProfileService {

    private final CustomerProfileRepository repository;

    public CustomerProfileService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerProfileResponse create(CustomerProfileCreateRequest dto) {
        var entity = new CustomerProfileEntity()
                .setId(UUID.randomUUID().toString())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail());
        if (dto.getAddress() != null) {
            entity.setAddresses(List.of(
                    new AddressEntity()
                            .setId(UUID.randomUUID().toString())
                            .setMain(dto.getAddress().isMain())
                            .setStreet(dto.getAddress().getStreet())
                            .setCity(dto.getAddress().getCity())
                            .setZipCode(dto.getAddress().getZipCode())
            ));
        }

        var persistedEntity = repository.save(entity);
        return entityToDto(persistedEntity);
    }

    @Transactional
    public Optional<CustomerProfileResponse> change(String id, CustomerProfileChangeRequest dto) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setFirstName(dto.getFirstName());
                    entity.setLastName(dto.getLastName());
                    return repository.save(entity);
                })
                .map(this::entityToDto);
    }

    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Stream<CustomerProfileResponse> getAll() {
        return repository.streamAll().map(this::entityToDto);
    }

    public Optional<CustomerProfileResponse> getById(String idRepresentation) {
        return repository.findById(idRepresentation).map(this::entityToDto);
    }

    private CustomerProfileResponse entityToDto(CustomerProfileEntity entity) {
        return new CustomerProfileResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail())
                .setAddresses(
                        entity.getAddresses().stream()
                                .map(address -> new Address(address.getStreet(), address.getCity(), address.getZipCode()).setMain(address.isMain()))
                                .collect(Collectors.toList()));
    }
}
