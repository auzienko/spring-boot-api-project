package ru.myapp.contactbook.services;

import ru.myapp.contactbook.models.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Employee> {
    List<Employee> findByPhoneNumberId(Long id);

    List<Employee> findByPhoneNumberNumber(String phoneNumber);
}
