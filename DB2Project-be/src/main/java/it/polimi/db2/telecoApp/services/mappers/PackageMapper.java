package it.polimi.db2.telecoApp.services.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.services.enums.PackageType;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.packagedetails.ServiceDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ServiceMapper.class)
public interface PackageMapper {
    PackageMapper MAPPER = Mappers.getMapper(PackageMapper.class);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "services", source = "services")
    Package toTarget(ServicePackageEntity source);

    @Mapping(source = "id"  , target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "services", target = "services")
    ServicePackageEntity toSource(Package source);



}
