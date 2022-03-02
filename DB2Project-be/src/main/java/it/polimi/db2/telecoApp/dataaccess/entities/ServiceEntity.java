package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class ServiceEntity {
    @Id
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_details")
    private String serviceDetails;
}
