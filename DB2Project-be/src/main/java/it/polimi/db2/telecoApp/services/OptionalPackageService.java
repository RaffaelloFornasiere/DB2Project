package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.OptionalPackage;

import java.util.List;

public interface OptionalPackageService {
    List<OptionalPackage> findAll();

    List<OptionalPackage> findAllByPackageId(Long id);

    OptionalPackage save(OptionalPackage optionalPackage);
}
