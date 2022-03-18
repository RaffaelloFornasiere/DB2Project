package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.services.models.OptionalPackage;

import java.util.List;

public interface OptionalPackageService {
    List<OptionalPackage> findAll();

    List<OptionalPackage> findAllByPackageId(Long id);

    OptionalPackage save(OptionalPackage optionalPackage);

    void delete(Long optionalPackageId);
}
