package it.polimi.db2.telecoApp.services;

import it.polimi.db2.telecoApp.services.models.ValidityPeriod;

import java.util.List;

public interface ValidityPeriodService {
    List<ValidityPeriod> findAllByPackageId(Long packageId);
    List<ValidityPeriod> findAll();
}