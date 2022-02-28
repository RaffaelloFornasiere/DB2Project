package it.polimi.db2.telecoApp.dataaccess.entities.ids;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class SalesValidityId implements Serializable {

    @Column(name = "validity_period_id", nullable = false)
    private Long id;

    @Column(name = "package_id", nullable = false)
    private Long package_id;

}

