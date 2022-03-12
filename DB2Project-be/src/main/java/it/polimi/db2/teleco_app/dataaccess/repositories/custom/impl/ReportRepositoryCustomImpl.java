package it.polimi.db2.teleco_app.dataaccess.repositories.custom.impl;

import it.polimi.db2.teleco_app.dataaccess.entities.SalesOptionalReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesPackageReportEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.SalesValidityReportEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.ReportRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
        Query query = entityManager.createQuery("select a from SalesValidityReportEntity a");
        var res = query.getResultList();
        return (List<SalesValidityReportEntity>) res;
    }

    @Override
    public List<SalesPackageReportEntity> findAllPackageReport() {
        Query query = entityManager.createQuery("select a from SalesPackageReportEntity a");
        var res = query.getResultList();
        return (List<SalesPackageReportEntity>) res;
    }
    @Override
    public List<SalesOptionalReportEntity> findAllOptionalReport() {
        Query query = entityManager.createQuery("select a from SalesOptionalReportEntity a order by a.score desc");
        var res = query.getResultList();
        return (List<SalesOptionalReportEntity>) res;
    }

    @Override
    public Map<String, Object> getUserCumulativeServices(String username){
        Query query = entityManager.createNativeQuery("select user, minutes, sms, gigabytes, cost from user_accumulative_services where user = :username")
                .setParameter("username", username);
        var res = (Object[])query.getSingleResult();

        return Map.of(
                "minutes",  res[1]== null?0:res[1],
                "sms", res[2]== null?0:res[2],
                "gigabytes", res[3]== null?0:res[3],
                "cost",  res[4]== null?0:res[4]);
    }
}