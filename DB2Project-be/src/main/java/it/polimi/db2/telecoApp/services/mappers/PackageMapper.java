package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.PackageEntity;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.packagedetails.PackageDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    PackageMapper MAPPER = Mappers.getMapper(PackageMapper.class);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Package toTarget(PackageEntity source);

    @Mapping(source = "id"  , target = "id")
    @Mapping(source = "name", target = "name")
    PackageEntity toSource(Package source);

    default PackageDetails map(String value){
        return null;
    }

    default String map(PackageDetails value){
        return null;
    }
}
