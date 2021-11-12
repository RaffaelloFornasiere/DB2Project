package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "users_username_uindex", columnNames = {"username"})
})
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "billing_addr")
    private String billingAddr;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "insolvent", nullable = false)
    private Boolean insolvent = false;
}