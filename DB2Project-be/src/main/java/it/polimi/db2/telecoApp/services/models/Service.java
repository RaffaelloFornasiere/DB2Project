package it.polimi.db2.telecoApp.services.models;


import it.polimi.db2.telecoApp.services.models.packagedetails.ServiceDetails;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Service {
    private Integer serviceId;
    private String serviceName;
    private ServiceDetails serviceDetails;
}
