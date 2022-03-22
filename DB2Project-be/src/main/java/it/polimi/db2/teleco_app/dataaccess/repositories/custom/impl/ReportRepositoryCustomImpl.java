package it.polimi.db2.teleco_app.dataaccess.repositories.custom.impl;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesOptionalReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesPackageReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.ReportRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<SalesValidityReportEntity> findAllValidityReport() {
        return entityManager.createQuery("select a from SalesValidityReportEntity a", SalesValidityReportEntity.class)
                .getResultList();
    }

    @Override
    public List<SalesPackageReportEntity> findAllPackageReport() {
        return entityManager.createQuery("select a from SalesPackageReportEntity a", SalesPackageReportEntity.class)
                .getResultList();
    }

    @Override
    public List<SalesOptionalReportEntity> findAllOptionalReport() {
        return entityManager.createQuery("select a from SalesOptionalReportEntity a order by a.score desc", SalesOptionalReportEntity.class)
                .getResultList();
    }

    @Override
    public Map<String, Object> getUserCumulativeServices(String username) {
        Query query = entityManager.createNativeQuery("select user, minutes, sms, gigabytes, cost from user_accumulative_services where user = :username")
                .setParameter("username", username);
        Object[] res;
        try {
            res = (Object[]) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return Map.of(
                "minutes", res[1] == null ? 0 : res[1],
                "sms", res[2] == null ? 0 : res[2],
                "gigabytes", res[3] == null ? 0 : res[3],
                "cost", res[4] == null ? 0 : res[4]);
    }
}