package it.polimi.db2.teleco_app.dataaccess.entities;

import it.polimi.db2.teleco_app.dataaccess.entities.ids.SalesValidityId;
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
@Table(name = "sales_validity_report")
public class SalesValidityReportEntity {
    @EmbeddedId
    private SalesValidityId salesValidityId;

    @Column(name = "total")
    private Integer total;
}

