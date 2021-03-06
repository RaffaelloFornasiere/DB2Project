package it.polimi.db2.teleco_app.services.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Order {
    private Long id;
    private LocalDateTime orderDate;
    private String user;
    private Package servicePackage;
    private ValidityPeriod validityPeriod;
    private List<OptionalPackage> optionalPackages;
    private LocalDate startDate;
    private Double totalValue;
}
