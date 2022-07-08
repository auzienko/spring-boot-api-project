package ru.myapp.contactbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myapp.contactbook.constants.DepartmentControllerTexts;
import ru.myapp.contactbook.models.Department;
import ru.myapp.contactbook.services.DepartmentService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = DepartmentControllerTexts.TAG_NAME, description = DepartmentControllerTexts.TAG_DESCRIPTION)
@RequestMapping("/api/ver/")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService DepartmentService) {
        this.departmentService = DepartmentService;
    }

    @Operation(
            summary = DepartmentControllerTexts.GETALL_SUMMARY,
            description = DepartmentControllerTexts.GETALL_DESCRIPTION
    )
    @GetMapping("1-0-0/departments")
    public List<Department> getAll_1_0_0() {
        return departmentService.findAll();
    }

    @Operation(
            summary = DepartmentControllerTexts.ADDNEW_SUMMARY,
            description = DepartmentControllerTexts.ADDNEW_DESCRIPTION
    )
    @PostMapping("1-0-0/departments")
    public Department addNew_1_0_0(@RequestBody Department entity) {
        return departmentService.save(entity);
    }

    @Operation(
            summary = DepartmentControllerTexts.GETONE_SUMMARY,
            description = DepartmentControllerTexts.GETONE_DESCRIPTION
    )
    @GetMapping("1-0-0/departments/{id}")
    public Department getOne_1_0_0(@PathVariable Long id) {
        Optional<Department> result = departmentService.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Operation(
            summary = DepartmentControllerTexts.UPDATEONE_SUMMARY,
            description = DepartmentControllerTexts.UPDATEONE_DESCRIPTION
    )
    @PutMapping("1-0-0/departments/{id}")
    public Department updateOne_1_0_0(@RequestBody Department entity, @PathVariable Long id) {
        Optional<Department> optionalDepartment = departmentService.findById(id);
        if (optionalDepartment.isPresent()) {
            entity.setId(id);
        }
        return departmentService.save(entity);
    }

    @Operation(
            summary = DepartmentControllerTexts.DELETEONE_SUMMARY,
            description = DepartmentControllerTexts.DELETEONE_DESCRIPTION
    )
    @DeleteMapping("1-0-0/departments/{id}")
    public ResponseEntity<?> deleteOne_1_0_0(@PathVariable Long id) {
        Optional<Department> byId = departmentService.findById(id);
        if (byId.isPresent()) {
            departmentService.deleteById(id);
            return new ResponseEntity<>(MessageFormat.format(DepartmentControllerTexts.DELETE_MSG_SUCCESS, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(MessageFormat.format(DepartmentControllerTexts.DELETE_MSG_FAIL_NOTFOUND, id), HttpStatus.BAD_REQUEST);
    }
}
