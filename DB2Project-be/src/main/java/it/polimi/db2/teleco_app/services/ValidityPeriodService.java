package it.polimi.db2.teleco_app.services;

import it.polimi.db2.teleco_app.services.models.ValidityPeriod;

import java.util.List;


public interface ValidityPeriodService {
    List<ValidityPeriod> findAllByPackageId(Long packageId);

    List<ValidityPeriod> findAll();

    ValidityPeriod save(ValidityPeriod validityPeriod);

    void delete(Long validityPeriodId);
}