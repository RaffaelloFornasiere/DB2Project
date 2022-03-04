package it.polimi.db2.teleco_app.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "users_username_uindex", columnNames = {"username"})
})
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "billing_addr")
    private String billingAddr;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<RoleEntity> roles;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    private Set<OrderEntity> purchasesEntities;


    @Column(name = "insolvent")
    private Boolean insolvent;

}