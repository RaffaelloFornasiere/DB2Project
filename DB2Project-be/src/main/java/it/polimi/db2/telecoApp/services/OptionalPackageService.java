package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.OptionalPackage;

import java.util.List;

public interface OptionalPackageService {
    List<OptionalPackage> findAll();
    OptionalPackage save(OptionalPackage optionalPackage);
}
