package it.polimi.db2.teleco_app.dataaccess.repositories.custom;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesOptionalReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesPackageReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;

import java.util.List;

public interface ReportRepositoryCustom {
    List<SalesValidityReportEntity> findAllValidityReport();

    List<SalesPackageReportEntity> findAllPackageReport();

    List<SalesOptionalReportEntity> findAllOptionalReport();
}
