package ru.myapp.contactbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myapp.contactbook.models.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByPhoneNumber_Id(Long id);

    List<Employee> findEmployeesByPhoneNumber_PhoneNumber(String phoneNumber);
}
