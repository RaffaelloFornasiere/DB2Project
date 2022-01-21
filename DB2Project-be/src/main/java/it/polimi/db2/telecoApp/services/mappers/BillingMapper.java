package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.BillingEntity;
import it.polimi.db2.telecoApp.services.models.Billing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "result", target = "result")
    BillingEntity toSource(Billing billing);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "result", target = "result")
    Billing toTarget(BillingEntity billing);
}
