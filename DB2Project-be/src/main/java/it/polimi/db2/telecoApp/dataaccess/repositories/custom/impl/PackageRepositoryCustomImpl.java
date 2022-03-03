package it.polimi.db2.telecoApp.dataaccess.repositories.custom.impl;

import it.polimi.db2.telecoApp.dataaccess.entities.OptionalPackageEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.ValidityPeriodEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.custom.PackageRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PackageRepositoryCustomImpl implements PackageRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServicePackageEntity savePackageWithDetails(ServicePackageEntity packageEntity, List<OptionalPackageEntity> optionals, List<ValidityPeriodEntity> validityPeriods) {
        if (packageEntity.getId() != null && entityManager.find(ServicePackageEntity.class, packageEntity.getId()) != null)
            entityManager.merge(packageEntity);
        else
            entityManager.persist(packageEntity);

        storePackageOptionalPackageRelation(packageEntity, optionals);
        storePackageValidityPeriodRelation(packageEntity, validityPeriods);

        return packageEntity;
    }

    private void storePackageValidityPeriodRelation(ServicePackageEntity packageEntity, List<ValidityPeriodEntity> validityPeriods) {
        var current = (List<Integer>) entityManager.createNativeQuery("select validityPeriodId from validity_period_package where packageId = :packageId")
                .setParameter("packageId", packageEntity.getId()).getResultList();
        var needed = validityPeriods.stream().map(ValidityPeriodEntity::getId).toList();
        var toBeAdded = new ArrayList<>(needed);
        var toBeRemoved = new ArrayList<>(current);
        toBeRemoved.removeAll(needed);
        toBeAdded.removeAll(current);
        if (!toBeAdded.isEmpty())
            entityManager.createNativeQuery("""
                                                INSERT INTO validity_period_package (packageId, validityPeriodId) 
                                                (select :packageId, vp.id from validity_periods vp where vp.id in :toBeAdded)
                            """)
                    .setParameter("packageId", packageEntity.getId())
                    .setParameter("toBeAdded", toBeAdded)
                    .executeUpdate();
        if (!toBeRemoved.isEmpty())
            entityManager.createNativeQuery("""
                                            DELETE from validity_period_package vp where vp.validityPeriodId in :toBeRemoved and vp.packageId = :packageId
                            """)
                    .setParameter("packageId", packageEntity.getId())
                    .setParameter("toBeRemoved", toBeRemoved)
                    .executeUpdate();
        entityManager.flush();
    }

    private void storePackageOptionalPackageRelation(ServicePackageEntity packageEntity, List<OptionalPackageEntity> optionalPackages) {
        var current = (List<Integer>) entityManager.createNativeQuery("select id_optional_package from packages_optional_packages where id_service_package = :packageId")
                .setParameter("packageId", packageEntity.getId()).getResultList();
        var needed = optionalPackages.stream().map(OptionalPackageEntity::getId).toList();
        var toBeAdded = new ArrayList<>(needed);
        var toBeRemoved = new ArrayList<>(current);
        toBeRemoved.removeAll(needed);
        toBeAdded.removeAll(current);
        if (!toBeAdded.isEmpty())
            entityManager.createNativeQuery("""
                                                INSERT INTO packages_optional_packages (id_service_package, id_optional_package) 
                                                (select :packageId, op.id from optional_packages op where op.id in :toBeAdded)
                            """)
                    .setParameter("packageId", packageEntity.getId())
                    .setParameter("toBeAdded", toBeAdded)
                    .executeUpdate();
        if (!toBeRemoved.isEmpty())
            entityManager.createNativeQuery("""
                                            DELETE from packages_optional_packages op where op.id_optional_package in :toBeRemoved and op.id_service_package = :packageId
                            """)
                    .setParameter("packageId", packageEntity.getId())
                    .setParameter("toBeRemoved", toBeRemoved)
                    .executeUpdate();
        entityManager.flush();
    }
}
