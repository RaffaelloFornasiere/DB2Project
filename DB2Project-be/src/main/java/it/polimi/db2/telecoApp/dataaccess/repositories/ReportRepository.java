package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.custom.ReportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportRepository extends JpaRepository<SalesValidityReportEntity, Long>,
        ReportRepositoryCustom {
}
