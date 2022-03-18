package it.polimi.db2.teleco_app.dataaccess.entities.ids;

import it.polimi.db2.teleco_app.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.ValidityPeriodEntity;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class SalesValidityId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "validity_period_id", nullable = false)
    private ValidityPeriodEntity validityPeriod;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private ServicePackageEntity servicePackage;

}

