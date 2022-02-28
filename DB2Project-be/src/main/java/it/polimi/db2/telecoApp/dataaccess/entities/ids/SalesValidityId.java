package it.polimi.db2.telecoApp.dataaccess.entities.ids;

import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.ValidityPeriodEntity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;


@Embeddable
public class SalesValidityId implements Serializable {

    @OneToOne
    @JoinColumn(name = "validity_period_id", nullable = false)
    private ValidityPeriodEntity id;

    @OneToOne
    @JoinColumn(name = "package_id", nullable = false)
    private ServicePackageEntity package_id;

}

