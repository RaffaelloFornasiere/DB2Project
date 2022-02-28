package it.polimi.db2.telecoApp.dataaccess.entities;

import it.polimi.db2.telecoApp.dataaccess.entities.ids.SalesValidityId;
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

    @EmbeddedId
    private SalesValidityId salesValidityId;


    @Column(name = "total")
    private Integer total;

}

