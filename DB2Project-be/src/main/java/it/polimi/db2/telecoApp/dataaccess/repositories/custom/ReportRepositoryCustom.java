package it.polimi.db2.telecoApp.dataaccess.repositories.custom;

import it.polimi.db2.telecoApp.dataaccess.entities.SalesValidityReportEntity;

import java.util.List;

public interface ReportRepositoryCustom {
    List<SalesValidityReportEntity> findAllCustom();

}
