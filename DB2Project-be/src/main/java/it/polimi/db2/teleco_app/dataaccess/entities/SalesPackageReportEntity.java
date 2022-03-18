package it.polimi.db2.teleco_app.dataaccess.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@Entity
@EntityListeners(ReadOnlyEntity.class)
@Table(name = "sales_package_report")
public class SalesPackageReportEntity {
    @Id
    @Column(name = "package_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id", insertable = false, updatable = false)
    private ServicePackageEntity servicePackage;

    @Column(name = "total_sold")
    private Integer totalSold;

    @Column(name = "total_w_optional")
    private Integer totalWOptional;

    @Column(name = "total_wo_optional")
    private Integer totalWoOptional;

    @Column(name = "average_optional")
    private Double averageOptional;
}
