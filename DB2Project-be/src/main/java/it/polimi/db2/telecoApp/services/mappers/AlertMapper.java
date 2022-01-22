package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;
import it.polimi.db2.telecoApp.services.models.Alert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    Alert toTarget(AlertEntity alertEntity);


    AlertEntity toSource(Alert alert);
}
