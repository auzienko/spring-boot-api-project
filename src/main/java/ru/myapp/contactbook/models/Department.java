package ru.myapp.contactbook.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.myapp.contactbook.constants.DepartmentModelTexts;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "department")
@Schema(description = DepartmentModelTexts.DESCRIPTION)
public class Department extends BaseEntity{
    @Schema(description = DepartmentModelTexts.NAME_DESCRIPTION, example = DepartmentModelTexts.NAME_EXAMPLE)
    @Column(name = "name")
    private String name;

    @Schema(description = DepartmentModelTexts.BOSS_DESCRIPTION)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boss", referencedColumnName = "id")
    @JsonIgnoreProperties("departmentList")
    private Employee boss;

    @Schema(description = DepartmentModelTexts.ADDRESS_DESCRIPTION)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Schema(description = DepartmentModelTexts.PHONENUMBER_DESCRIPTION)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "phone_number_id", referencedColumnName = "id")
    private PhoneNumber phoneNumber;

    @Schema(description = DepartmentModelTexts.EMPLOYEELIST_DESCRIPTION)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "department_employee",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnoreProperties("departmentList")
    private List<Employee> employeeList;
}
