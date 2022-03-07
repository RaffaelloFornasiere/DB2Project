package it.polimi.db2.teleco_app.dataaccess.repositories;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.ids.SalesValidityId;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.ReportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportRepository extends JpaRepository<SalesValidityReportEntity, SalesValidityId>, ReportRepositoryCustom {
}
