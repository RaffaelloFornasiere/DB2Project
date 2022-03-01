package it.polimi.db2.telecoApp.services.models;

import it.polimi.db2.telecoApp.dataaccess.entities.ids.SalesValidityId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Report {
    SalesValidityId salesValidityId;
    Integer total;
}
