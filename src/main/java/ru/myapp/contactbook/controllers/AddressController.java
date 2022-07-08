package ru.myapp.contactbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myapp.contactbook.constants.AddressControllerTexts;
import ru.myapp.contactbook.models.Address;
import ru.myapp.contactbook.services.AddressService;
import ru.myapp.contactbook.services.DepartmentService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = AddressControllerTexts.TAG_NAME, description = AddressControllerTexts.TAG_DESCRIPTION)
@RequestMapping("/api/ver/")
public class AddressController {
    private final AddressService addressService;
    private final DepartmentService departmentService;

    public AddressController(AddressService addressService, DepartmentService departmentService) {
        this.addressService = addressService;
        this.departmentService = departmentService;
    }


    @Operation(
            summary = AddressControllerTexts.GETALL_SUMMARY,
            description = AddressControllerTexts.GETALL_DESCRIPTION
    )
    @GetMapping("1-0-0/addresses")
    public List<Address> getAll_1_0_0() {
        return addressService.findAll();
    }

    @Operation(
            summary = AddressControllerTexts.ADDNEW_SUMMARY,
            description = AddressControllerTexts.ADDNEW_DESCRIPTION
    )
    @PostMapping("1-0-0/addresses")
    public Address addNew_1_0_0(@RequestBody Address entity) {
        return addressService.save(entity);
    }

    @Operation(
            summary = AddressControllerTexts.GETONE_SUMMARY,
            description = AddressControllerTexts.GETONE_DESCRIPTION
    )
    @GetMapping("1-0-0/addresses/{id}")
    public Address getOne_1_0_0(@PathVariable Long id) {
        Optional<Address> result = addressService.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Operation(
            summary = AddressControllerTexts.UPDATEONE_SUMMARY,
            description = AddressControllerTexts.UPDATEONE_DESCRIPTION
    )
    @PutMapping("1-0-0/addresses/{id}")
    public Address updateOne_1_0_0(@RequestBody Address entity, @PathVariable Long id) {
        Optional<Address> optionalAddress = addressService.findById(id);
        if (optionalAddress.isPresent()) {
            entity.setId(id);
        }
        return addressService.save(entity);
    }

    @Operation(
            summary = AddressControllerTexts.DELETEONE_SUMMARY,
            description = AddressControllerTexts.DELETEONE_DESCRIPTION
    )
    @DeleteMapping("1-0-0/addresses/{id}")
    public ResponseEntity<?> deleteOne_1_0_0(@PathVariable Long id) {
        Optional<Address> byId = addressService.findById(id);
        if (byId.isPresent()) {
            if (departmentService.findDepatmentsByAddressId(id).size() != 0) {
                return new ResponseEntity<>(MessageFormat.format(AddressControllerTexts.DELETE_MSG_FAIL_ALREADYUSED, id), HttpStatus.BAD_REQUEST);
            }
            addressService.deleteById(id);
            return new ResponseEntity<>(MessageFormat.format(AddressControllerTexts.DELETE_MSG_SUCCESS, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(MessageFormat.format(AddressControllerTexts.DELETE_MSG_FAIL_NOTFOUND, id), HttpStatus.BAD_REQUEST);
    }
}
