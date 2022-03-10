package it.polimi.db2.teleco_app.services.models;

import it.polimi.db2.teleco_app.utils.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class UserReport {
    User user;
    List<Pair<Order, List<Billing>>> suspendedOrders;
    Alert alert;
}
