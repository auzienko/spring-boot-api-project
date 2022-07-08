package ru.myapp.contactbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myapp.contactbook.models.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepatmentsByPhoneNumber_Id(Long id);

    List<Department> findDepatmentsByPhoneNumber_PhoneNumber(String phoneNumber);

    List<Department> findDepatmentsByAddress_Id(Long id);
}
