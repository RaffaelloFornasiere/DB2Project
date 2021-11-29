package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;


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

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Long type;

    @Column(name = "details")
    private String details;
}
