package ru.myapp.contactbook.models;

import lombok.*;

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
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "role_id")
    private Role role;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
}
