package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.services.models.Package;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ServiceMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PackageMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "telecomServices", source = "services")
    Package toTarget(ServicePackageEntity source);

    @Mapping(source = "id"  , target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "telecomServices", target = "services")
    ServicePackageEntity toSource(Package source);



}
