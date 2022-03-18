package it.polimi.db2.teleco_app.security.payload;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.polimi.db2.teleco_app.services.models.User;
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
    private String token;
    @Builder.Default
    private String type = "Bearer";
    public JwtResponse(String jwt, User user) {
        super(user.toBuilder());
        this.token = jwt;
        type = "Bearer";
    }
}
