package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


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
            name = "packages_services",
            joinColumns = {@JoinColumn(name = "package_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")})
    private List<ServiceEntity> services;
}
