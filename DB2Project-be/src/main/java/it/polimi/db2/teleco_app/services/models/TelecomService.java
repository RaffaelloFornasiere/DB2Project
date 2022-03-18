package it.polimi.db2.teleco_app.services.models;


import it.polimi.db2.teleco_app.services.models.packagedetails.ServiceDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class TelecomService {
    private Integer id;
    private String name;
    private ServiceDetails details;
}
