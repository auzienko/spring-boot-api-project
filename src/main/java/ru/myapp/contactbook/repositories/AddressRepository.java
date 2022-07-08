package ru.myapp.contactbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myapp.contactbook.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
