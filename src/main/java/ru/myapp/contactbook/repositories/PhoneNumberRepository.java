package ru.myapp.contactbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myapp.contactbook.models.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
