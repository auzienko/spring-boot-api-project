package ru.myapp.contactbook.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.myapp.contactbook.constants.AddressModelTexts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "address")
@Schema(description = AddressModelTexts.DESCRIPTION)
public class Address extends BaseEntity {
    @Schema(description = AddressModelTexts.FIST_DESCRIPTION, example = AddressModelTexts.FIRST_EXAMPLE)
    @Column(name = "first")
    private String first;
    @Schema(description = AddressModelTexts.SECOND_DESCRIPTION, example = AddressModelTexts.SECOND_EXAMPLE)
    @Column(name = "second")
    private String second;
}
