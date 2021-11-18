package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "PACKAGES")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class PackageEntity {
    @Id
    private Long id;

    @Column
    private String name;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "packageEntity")
    private Set<PurchasesEntity> purchasesEntities;
}
