package it.polimi.db2.teleco_app.services.mappers;

import it.polimi.db2.teleco_app.dataaccess.entities.ValidityPeriodEntity;
import it.polimi.db2.teleco_app.services.models.ValidityPeriod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ValidityPeriodMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "months", target = "months")
    @Mapping(source = "fee", target = "fee")
    ValidityPeriod toTarget(ValidityPeriodEntity validityPeriod);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "months", target = "months")
    @Mapping(source = "fee", target = "fee")
    ValidityPeriodEntity toSource(ValidityPeriod validityPeriod);

}
