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
@Table(name = "SalesValidityReport")
public class SalesValidityReportEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePackageEntity package_id;

    @Id
    @OneToOne
    @JoinColumn(name = "validity_period_id")
    private ValidityPeriodEntity id;

    @Column(name = "total")
    private Integer total;



}
