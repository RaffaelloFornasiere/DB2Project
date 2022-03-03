package it.polimi.db2.telecoApp.dataaccess.repositories.custom;

import it.polimi.db2.telecoApp.dataaccess.entities.OptionalPackageEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.ValidityPeriodEntity;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.ValidityPeriod;

import java.util.List;


public interface PackageRepositoryCustom {
    ServicePackageEntity savePackageWithDetails(ServicePackageEntity packageEntity,
                                                List<OptionalPackageEntity> optionals,
                                                List<ValidityPeriodEntity> validityPeriods);
}
