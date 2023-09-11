package com.example.dbmigration.theapp.data;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CustomerProfileRepositoryTest {

    @Autowired
    private CustomerProfileRepository subject;

	@Autowired
	private EntityManager entityManager;

    @Test
	void shouldPersistCustomerProfileWithAddress() {
		var id = UUID.randomUUID().toString();
		var address = new AddressEntity()
				.setId(UUID.randomUUID().toString())
				.setCity("Munich")
				.setZipCode("89000")
				.setStreet("Milky Way 42");
		var entity = new CustomerProfileEntity()
				.setId(id)
				.setFirstName("Joe")
				.setLastName("Doe")
				.setEmail("joe.doe@test.com")
				.setAddress(address);

		subject.saveAndFlush(entity);

		entityManager.clear();

		var actual = subject.findById(id);
		assertThat(actual).isPresent();
		var actualEntity = actual.get();
		assertThat(actualEntity).isNotSameAs(entity);
		assertThat(actualEntity.getFirstName()).isEqualTo(entity.getFirstName());
		assertThat(actualEntity.getLastName()).isEqualTo(entity.getLastName());
		assertThat(actualEntity.getEmail()).isEqualTo(entity.getEmail());
		assertThat(actualEntity.getAddress()).isNotNull();
	}
}
