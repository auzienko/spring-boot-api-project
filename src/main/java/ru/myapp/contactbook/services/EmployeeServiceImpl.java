package ru.myapp.contactbook.services;

import org.springframework.stereotype.Service;
import ru.myapp.contactbook.models.Employee;
import ru.myapp.contactbook.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByPhoneNumberId(Long id) {
        return employeeRepository.findEmployeesByPhoneNumber_Id(id);
    }

    @Override
    public List<Employee> findByPhoneNumberNumber(String phoneNumber) {
        return employeeRepository.findEmployeesByPhoneNumber_PhoneNumber(phoneNumber);
    }
}
