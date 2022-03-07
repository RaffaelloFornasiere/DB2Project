package it.polimi.db2.teleco_app.services.mappers;


import it.polimi.db2.teleco_app.dataaccess.entities.OrderEntity;
import it.polimi.db2.teleco_app.services.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        OptionalPackageMapper.class,
        PackageMapper.class,
        ValidityPeriodMapper.class,
        UserMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderDate", source = "orderDate")
    @Mapping(target = "servicePackage", source = "servicePackageEntity")
    @Mapping(target = "validityPeriod", source = "validityPeriod")
    @Mapping(target = "optionalPackages", source = "optionalPackages")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "user", source = "user")
    Order toTarget(OrderEntity source);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderDate", source = "orderDate")
    @Mapping(target = "servicePackageEntity", source = "servicePackage")
    @Mapping(target = "validityPeriod", source = "validityPeriod")
    @Mapping(target = "optionalPackages", source = "optionalPackages")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "user", source = "user")
    OrderEntity toSource(Order source);


}
