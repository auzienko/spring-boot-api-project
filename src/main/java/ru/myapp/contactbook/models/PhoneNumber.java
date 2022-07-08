package ru.myapp.contactbook.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.myapp.contactbook.constants.PhoneNumberModelTexts;

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
@Table(name = "phone_number")
@Schema(description = PhoneNumberModelTexts.DESCRIPTION)
public class PhoneNumber extends BaseEntity {
    @Schema(description = PhoneNumberModelTexts.PHONENUMBER_DESCRIPTION, example = PhoneNumberModelTexts.PHONENUMBER_EXAMPLE)
    @Column(name = "phone_number")
    private String phoneNumber;
}
