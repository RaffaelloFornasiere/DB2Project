package it.polimi.db2.teleco_app.dataaccess.repositories.custom;

import it.polimi.db2.teleco_app.dataaccess.entities.OrderEntity;
import it.polimi.db2.teleco_app.services.models.Order;

public interface OrderRepositoryCustom {
    OrderEntity saveCustom(OrderEntity orderEntity);
}
