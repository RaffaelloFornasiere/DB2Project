package it.polimi.db2.teleco_app.dataaccess.entities.ids;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SalesValidityId implements Serializable {

    @Column(name = "validity_period_id", nullable = false)
    private Long id;

    @Column(name = "package_id", nullable = false)
    private Long packageId;

}

