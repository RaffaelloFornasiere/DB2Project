package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import it.polimi.db2.telecoApp.services.models.Package;

import java.util.List;

public interface PackageService {
    List<Package> findAll();
    Package findById(Long packageId);

    Package getDetails(Long id);
    List<OptionalPackage> getOptionalPackage(Long id);
    Package save (Package p);

}
