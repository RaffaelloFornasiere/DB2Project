package it.polimi.db2.teleco_app.dataaccess.repositories.custom;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;

import java.util.List;

public interface ReportRepositoryCustom {
    List<SalesValidityReportEntity> findAllCustom();

}
