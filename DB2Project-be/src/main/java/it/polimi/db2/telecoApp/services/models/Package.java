package it.polimi.db2.telecoApp.services.models;


import it.polimi.db2.telecoApp.dataaccess.entities.PackageTypeEntity;
import it.polimi.db2.telecoApp.services.enums.PackageType;
import it.polimi.db2.telecoApp.services.models.packagedetails.PackageDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class Package {
    private Long id;
    private String name;
    private PackageDetails details;
    private PackageType type;
}
