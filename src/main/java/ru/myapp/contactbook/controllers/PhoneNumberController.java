package ru.myapp.contactbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myapp.contactbook.constants.PhoneNumberControllerTexts;
import ru.myapp.contactbook.models.PhoneNumber;
import ru.myapp.contactbook.services.DepartmentService;
import ru.myapp.contactbook.services.EmployeeService;
import ru.myapp.contactbook.services.PhoneNumberService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = PhoneNumberControllerTexts.TAG_NAME, description = PhoneNumberControllerTexts.TAG_DESCRIPTION)
@RequestMapping("/api/ver/")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public PhoneNumberController(PhoneNumberService PhoneNumberService, EmployeeService employeeService, DepartmentService departmentService) {
        this.phoneNumberService = PhoneNumberService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Operation(
            summary = PhoneNumberControllerTexts.GETALL_SUMMARY,
            description = PhoneNumberControllerTexts.GETALL_DESCRIPTION
    )
    @GetMapping("1-0-0/phone-numbers")
    public List<PhoneNumber> getAll_1_0_0() {
        return phoneNumberService.findAll();
    }


    @Operation(
            summary = PhoneNumberControllerTexts.ADDNEW_SUMMARY,
            description = PhoneNumberControllerTexts.ADDNEW_DESCRIPTION
    )
    @PostMapping("1-0-0/phone-numbers")
    public PhoneNumber addNew_1_0_0(@RequestBody PhoneNumber entity) {
        return phoneNumberService.save(entity);
    }

    @Operation(
            summary = PhoneNumberControllerTexts.GETONE_SUMMARY,
            description = PhoneNumberControllerTexts.GETONE_DESCRIPTION
    )
    @GetMapping("1-0-0/phone-numbers/{id}")
    public PhoneNumber getOne_1_0_0(@PathVariable Long id) {
        Optional<PhoneNumber> result = phoneNumberService.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Operation(
            summary = PhoneNumberControllerTexts.UPDATEONE_SUMMARY,
            description = PhoneNumberControllerTexts.UPDATEONE_DESCRIPTION
    )
    @PutMapping("1-0-0/phone-numbers/{id}")
    public PhoneNumber updateOne_1_0_0(@RequestBody PhoneNumber entity, @PathVariable Long id) {
        Optional<PhoneNumber> optionalPhoneNumber = phoneNumberService.findById(id);
        if (optionalPhoneNumber.isPresent()) {
            entity.setId(id);
        }
        return phoneNumberService.save(entity);
    }

    @Operation(
            summary = PhoneNumberControllerTexts.DELETEONE_SUMMARY,
            description = PhoneNumberControllerTexts.DELETEONE_DESCRIPTION
    )
    @DeleteMapping("1-0-0/phone-numbers/{id}")
    public ResponseEntity<?> deleteOne_1_0_0(@PathVariable Long id) {
        Optional<PhoneNumber> byId = phoneNumberService.findById(id);
        if (byId.isPresent()) {
            if (departmentService.findByPhoneNumberId(id).size() != 0
                    || employeeService.findByPhoneNumberId(id).size() != 0) {
                return new ResponseEntity<>(MessageFormat.format(PhoneNumberControllerTexts.DELETE_MSG_FAIL_ALREADYUSED, id), HttpStatus.BAD_REQUEST);
            }
            phoneNumberService.deleteById(id);
            return new ResponseEntity<>(MessageFormat.format(PhoneNumberControllerTexts.DELETE_MSG_SUCCESS, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(MessageFormat.format(PhoneNumberControllerTexts.DELETE_MSG_FAIL_NOTFOUND, id), HttpStatus.BAD_REQUEST);
    }
}
