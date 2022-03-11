package it.polimi.db2.teleco_app.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "packages")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class ServicePackageEntity implements Serializable {
    @Id
    @Column(name = "package_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_name")
    private String name;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "packages_services", schema = "db2_pdb",
            joinColumns = {@JoinColumn(name = "package_id", referencedColumnName = "package_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "service_id")})
    private Set<ServiceEntity> services;
}
