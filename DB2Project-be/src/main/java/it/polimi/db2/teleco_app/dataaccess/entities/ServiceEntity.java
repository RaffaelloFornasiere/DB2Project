package it.polimi.db2.teleco_app.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@Entity
@Table(name = "services")
public class ServiceEntity implements Serializable {
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_details")
    private String serviceDetails;
}
