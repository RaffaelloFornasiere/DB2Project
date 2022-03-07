package it.polimi.db2.teleco_app.services.mappers;

import it.polimi.db2.teleco_app.dataaccess.entities.BillingEntity;
import it.polimi.db2.teleco_app.services.models.Billing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "result", target = "result")
    @Mapping(source = "billingDateTime", target = "billingDateTime")
    BillingEntity toSource(Billing billing);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "result", target = "result")
    @Mapping(source = "billingDateTime", target = "billingDateTime")
    Billing toTarget(BillingEntity billing);
}
