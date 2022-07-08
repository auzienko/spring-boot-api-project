package ru.myapp.contactbook.services;

import ru.myapp.contactbook.models.Department;

import java.util.List;

public interface DepartmentService extends BaseService<Department> {
    List<Department> findByPhoneNumberId(Long id);
    List<Department> findByPhoneNumberNumber(String phoneNumber);
    List<Department> findDepatmentsByAddressId(Long id);
}
