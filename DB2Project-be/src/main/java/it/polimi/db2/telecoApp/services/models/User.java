package it.polimi.db2.telecoApp.services.models;

import it.polimi.db2.telecoApp.services.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class User {
    private String username;
    private String name;
    private String surname;
    private String password;

    private Set<Role> roles = new HashSet<>();
}