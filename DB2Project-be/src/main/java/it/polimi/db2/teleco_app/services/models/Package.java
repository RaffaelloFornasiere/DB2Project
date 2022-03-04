package it.polimi.db2.teleco_app.services.models;


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
public class Package {
    private Long id;
    private String name;
    private List<TelecomService> telecomServices;
}
