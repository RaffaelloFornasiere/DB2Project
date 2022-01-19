package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.OptionalPackageEntity;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionalPackageMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "monthlyFee", target = "monthlyFee")
    OptionalPackage toTarget(OptionalPackageEntity sfgdfg);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "monthlyFee", target = "monthlyFee")
    OptionalPackage toSource(OptionalPackageEntity source);
}
