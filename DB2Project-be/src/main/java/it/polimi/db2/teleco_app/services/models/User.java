package it.polimi.db2.teleco_app.services.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.polimi.db2.teleco_app.services.enums.Role;
import it.polimi.db2.teleco_app.services.utilities.serialization.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@JsonSerialize(using = UserSerializer.class)
public class User implements UserDetails {

    @NotBlank
    private String username;
    private String name;
    private String surname;
    @NotBlank
    @Size(min = 6)
    private String password;
    private String email;
    private Boolean insolvent;

    @Builder.Default
    private Set<Role> roles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(
                r -> new SimpleGrantedAuthority(r.name())).collect(Collectors.toSet());
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
