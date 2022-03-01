package it.polimi.db2.telecoApp.dataaccess.repositories.custom.impl;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.custom.ReportRepositoryCustom;
import it.polimi.db2.telecoApp.services.mappers.ReportMapper;
import it.polimi.db2.telecoApp.services.models.OptionalPackage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<SalesValidityReportEntity> findAllCustom() {
        return (List<SalesValidityReportEntity>) entityManager.createQuery("select a from SalesValidityReportEntity a").getResultList();
    }
}