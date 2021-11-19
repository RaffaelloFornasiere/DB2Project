package it.polimi.db2.telecoApp.security.payload;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.polimi.db2.telecoApp.services.models.User;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@JsonSerialize
public class JwtResponse extends User {
    public JwtResponse(String jwt, User user)
    {
        super(user.toBuilder());
        this.token = jwt;
        type = "Bearer";
    }
    private String token;
    @Builder.Default
    private String type = "Bearer";
}
