package it.polimi.db2.teleco_app.dataaccess.repositories.custom.impl;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.ReportRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
@Transactional
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<SalesValidityReportEntity> findAllCustom() {
        Query query = entityManager.createQuery("select a from SalesValidityReportEntity a");
        var res = query.getResultList();
        return (List<SalesValidityReportEntity>) res;
    }
}