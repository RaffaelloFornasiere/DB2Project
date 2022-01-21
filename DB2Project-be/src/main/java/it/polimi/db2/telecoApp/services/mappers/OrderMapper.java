package it.polimi.db2.telecoApp.services.mappers;


import it.polimi.db2.telecoApp.dataaccess.entities.OrderEntity;
import it.polimi.db2.telecoApp.services.models.Order;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring", uses = {OptionalPackageMapper.class,
        PackageMapper.class,
        ValidityPeriodMapper.class})
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderDate", source = "orderDate")
    @Mapping(target = "servicePackage", source = "servicePackageEntity")
    @Mapping(target = "validityPeriod", source = "validityPeriod")
    @Mapping(target = "optionalPackages", source = "optionalPackages")
    @Mapping(target = "startDate", source = "startDate")
    Order toTarget(OrderEntity source);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderDate", source = "orderDate")
    @Mapping(target = "servicePackageEntity", source = "servicePackage")
    @Mapping(target = "validityPeriod", source = "validityPeriod")
    @Mapping(target = "optionalPackages", source = "optionalPackages")
    @Mapping(target = "startDate", source = "startDate")
    OrderEntity toSource(Order source);


}