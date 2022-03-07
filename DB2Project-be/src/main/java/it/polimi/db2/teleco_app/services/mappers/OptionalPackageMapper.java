package it.polimi.db2.teleco_app.services.mappers;

import it.polimi.db2.teleco_app.dataaccess.entities.OptionalPackageEntity;
import it.polimi.db2.teleco_app.services.models.OptionalPackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionalPackageMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "monthlyFee", target = "monthlyFee")
    OptionalPackage toTarget(OptionalPackageEntity source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "monthlyFee", target = "monthlyFee")
    OptionalPackageEntity toSource(OptionalPackage source);
}
