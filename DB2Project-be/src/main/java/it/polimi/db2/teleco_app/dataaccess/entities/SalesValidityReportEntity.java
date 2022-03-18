package it.polimi.db2.teleco_app.dataaccess.entities;

import it.polimi.db2.teleco_app.dataaccess.entities.ids.SalesValidityId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@Entity
@Table(name = "sales_validity_report")
public class SalesValidityReportEntity {
    @EmbeddedId
    private SalesValidityId salesValidityId;

    @Column(name = "total")
    private Integer total;
}

