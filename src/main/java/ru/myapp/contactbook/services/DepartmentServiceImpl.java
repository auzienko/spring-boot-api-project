package ru.myapp.contactbook.services;

import org.springframework.stereotype.Service;
import ru.myapp.contactbook.models.Department;
import ru.myapp.contactbook.repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department entity) {
        return departmentRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> findByPhoneNumberId(Long id) {
        return departmentRepository.findDepatmentsByPhoneNumber_Id(id);
    }

    @Override
    public List<Department> findByPhoneNumberNumber(String phoneNumber) {
        return departmentRepository.findDepatmentsByPhoneNumber_PhoneNumber(phoneNumber);
    }

    @Override
    public List<Department> findDepatmentsByAddressId(Long id){
        return departmentRepository.findDepatmentsByAddress_Id(id);
    }
}
