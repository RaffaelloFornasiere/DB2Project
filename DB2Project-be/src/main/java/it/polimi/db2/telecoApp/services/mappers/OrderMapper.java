package it.polimi.db2.telecoApp.services.mappers;


import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "purchase_date", source = "order_date")
    @Mapping(target ="package", source = "package")

}
