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
@Table(name = "sales_ackage_eport")
public class SalesPackageReportEntity {

    @Id
    @Column(name = "package_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id", insertable = false, updatable = false)
    private ServicePackageEntity servicePackage;

    @Column(name = "total_sold")
    private Integer total_sold;

    @Column(name = "total_w_optional")
    private Integer total_w_optional;

    @Column(name = "total_wo_optional")
    private Integer total_wo_optional;

    @Column(name = "average_optional")
    private Double average_optional;


}
