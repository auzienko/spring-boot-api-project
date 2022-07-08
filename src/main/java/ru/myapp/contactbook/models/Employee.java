package ru.myapp.contactbook.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.myapp.contactbook.constants.EmployeeModelTexts;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employee")
@Schema(description = EmployeeModelTexts.DESCRIPTION)
public class Employee extends BaseEntity {

    @Schema(description = EmployeeModelTexts.FIRSTNAME_DESCRIPTION, example = EmployeeModelTexts.FIRSTNAME_EXAMPLE)
    @Column(name = "first_name")
    private String firstName;

    @Schema(description = EmployeeModelTexts.LASTNAME_DESCRIPTION, example = EmployeeModelTexts.LASTTNAME_EXAMPLE)
    @Column(name = "last_name")
    private String lastName;

    @Schema(description = EmployeeModelTexts.PATRONYMIC_DESCRIPTION, example = EmployeeModelTexts.PATRONYMIC_EXAMPLE)
    @Column(name = "patronymic")
    private String patronymic;

    @Schema(description = EmployeeModelTexts.PHONENUMBER_DESCRIPTION)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "phone_number_id", referencedColumnName = "id")
    private PhoneNumber phoneNumber;

    @Schema(description = EmployeeModelTexts.DEPARTMENTLIST_DESCRIPTION)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "department_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    @JsonIgnoreProperties("employeeList")
    private List<Department> departmentList;

}
