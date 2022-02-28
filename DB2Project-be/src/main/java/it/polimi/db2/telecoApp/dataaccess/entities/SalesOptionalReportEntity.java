package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@Table(name = "SalesOptionalReport")
public class SalesOptionalReportEntity {

    @Id
    @Column(name = "optional_package_id")
    private Long id;

    @OneToOne
    @JoinColumn (name = "optional_package_id", insertable = false, updatable = false)
    private OptionalPackageEntity optionalPackage;

    @Column(name = "score")
    private Double score;
}
