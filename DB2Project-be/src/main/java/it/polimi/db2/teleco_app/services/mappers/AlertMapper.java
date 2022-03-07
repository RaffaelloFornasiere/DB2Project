package it.polimi.db2.teleco_app.services.mappers;

import it.polimi.db2.teleco_app.dataaccess.entities.AlertEntity;
import it.polimi.db2.teleco_app.services.models.Alert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    Alert toTarget(AlertEntity alertEntity);


    AlertEntity toSource(Alert alert);
}
