package it.polimi.db2.telecoApp.services.mappers;


import it.polimi.db2.telecoApp.dataaccess.entities.OrderEntity;
import it.polimi.db2.telecoApp.services.models.Order;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateTime", source = "")
    @Mapping(target = "servicePackage", source = "")
    @Mapping(target ="validityPeriod", source = "")
    @Mapping(target ="optionalPackages", source = "")
    @Mapping(target ="startDate", source = "")

    Order toTarget(OrderEntity source);

}
