package it.polimi.db2.teleco_app.services.models;

import it.polimi.db2.teleco_app.dataaccess.entities.ids.SalesValidityId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Report {
    SalesValidityId salesValidityId;
    Integer total;
}
