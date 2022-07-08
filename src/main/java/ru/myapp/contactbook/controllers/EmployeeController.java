package ru.myapp.contactbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myapp.contactbook.constants.EmployeeControllerTexts;
import ru.myapp.contactbook.models.Employee;
import ru.myapp.contactbook.services.EmployeeService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = EmployeeControllerTexts.TAG_NAME, description = EmployeeControllerTexts.TAG_DESCRIPTION)
@RequestMapping("/api/ver/")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = EmployeeControllerTexts.GETALL_SUMMARY,
            description = EmployeeControllerTexts.GETALL_DESCRIPTION
    )
    @GetMapping("1-0-0/employees")
    public List<Employee> getAll_1_0_0() {
        return employeeService.findAll();
    }

    @Operation(
            summary = EmployeeControllerTexts.ADDNEW_SUMMARY,
            description = EmployeeControllerTexts.ADDNEW_DESCRIPTION
    )
    @PostMapping("1-0-0/employees")
    public Employee addNew_1_0_0(@RequestBody Employee entity) {
        return employeeService.save(entity);
    }

    @Operation(
            summary = EmployeeControllerTexts.FINDBYLASTNAME_SUMMARY,
            description = EmployeeControllerTexts.FINDBYLASTNAME_DESCRIPTION
    )
    @GetMapping("1-0-0/employees/search/lastname")
    public List<Employee> findByLastName_1_0_0(@RequestParam("query") String lastName) {
        return employeeService.findAll().stream().
                filter(employee -> employee.getLastName() != null && employee.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Operation(
            summary = EmployeeControllerTexts.FINDBYFIRSTNAME_SUMMARY,
            description = EmployeeControllerTexts.FINDBYFIRSTNAME_DESCRIPTION
    )
    @GetMapping("1-0-0/employees/search/firstname")
    public List<Employee> findByFirstName_1_0_0(@RequestParam("query") String firstName) {
        return employeeService.findAll().stream().
                filter(employee -> employee.getFirstName() != null && employee.getLastName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Operation(
            summary = EmployeeControllerTexts.GETONE_SUMMARY,
            description = EmployeeControllerTexts.GETONE_DESCRIPTION
    )
    @GetMapping("1-0-0/employees/{id}")
    public Employee getOne_1_0_0(@PathVariable Long id) {
        Optional<Employee> result = employeeService.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Operation(
            summary = EmployeeControllerTexts.UPDATEONE_SUMMARY,
            description = EmployeeControllerTexts.UPDATEONE_DESCRIPTION
    )
    @PutMapping("1-0-0/employees/{id}")
    public Employee updateOne_1_0_0(@RequestBody Employee entity, @PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            entity.setId(id);
        }
        return employeeService.save(entity);
    }

    @Operation(
            summary = EmployeeControllerTexts.DELETEONE_SUMMARY,
            description = EmployeeControllerTexts.DELETEONE_DESCRIPTION
    )
    @DeleteMapping("1-0-0/employees/{id}")
    public ResponseEntity<?> deleteOne_1_0_0(@PathVariable Long id) {
        Optional<Employee> byId = employeeService.findById(id);
        if (byId.isPresent()){
            employeeService.deleteById(id);
            return new ResponseEntity<>(MessageFormat.format(EmployeeControllerTexts.DELETE_MSG_SUCCESS, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(MessageFormat.format(EmployeeControllerTexts.DELETE_MSG_FAIL_NOTFOUND, id), HttpStatus.BAD_REQUEST);
    }
}
