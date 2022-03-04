package it.polimi.db2.teleco_app.dataaccess.repositories.custom;

import it.polimi.db2.teleco_app.dataaccess.entities.OptionalPackageEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.ValidityPeriodEntity;

import java.util.List;


public interface PackageRepositoryCustom {
    ServicePackageEntity savePackageWithDetails(ServicePackageEntity packageEntity,
                                                List<OptionalPackageEntity> optionals,
                                                List<ValidityPeriodEntity> validityPeriods);
}
