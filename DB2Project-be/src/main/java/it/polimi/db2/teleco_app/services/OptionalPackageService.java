package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.services.models.OptionalPackage;
import it.polimi.db2.teleco_app.services.models.Package;
import it.polimi.db2.teleco_app.utils.Pair;

import java.util.List;

public interface OptionalPackageService {
    List<OptionalPackage> findAll();

    List<OptionalPackage> findAllByPackageId(Long id);

    OptionalPackage save(OptionalPackage optionalPackage);

    List<Pair<Package, Double>> findAverageOptionalPackages();

    OptionalPackage getBestSeller();
}
