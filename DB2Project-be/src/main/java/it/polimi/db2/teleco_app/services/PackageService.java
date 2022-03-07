package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.services.models.OptionalPackage;
import it.polimi.db2.teleco_app.services.models.Package;
import it.polimi.db2.teleco_app.services.models.ValidityPeriod;

import java.util.List;

public interface PackageService {
    List<Package> findAll();
    Package findById(Long packageId);

    Package getDetails(Long id);
    List<OptionalPackage> getOptionalPackage(Long id);
    Package save (Package p);

    Package saveWithDetails(Package p, List<OptionalPackage> optionalPackages, List<ValidityPeriod> validityPeriods);

    void delete(Long packageId);
}
