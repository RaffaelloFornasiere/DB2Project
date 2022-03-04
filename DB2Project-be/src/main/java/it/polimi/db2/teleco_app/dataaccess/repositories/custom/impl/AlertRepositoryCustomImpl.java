package it.polimi.db2.teleco_app.dataaccess.repositories.custom.impl;

import it.polimi.db2.teleco_app.dataaccess.entities.AlertEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.AlertRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AlertRepositoryCustomImpl implements AlertRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<AlertEntity> findAllCustom() {
        return (List<AlertEntity>) entityManager.createQuery("select a from AlertEntity a").getResultList();
    }


    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public AlertEntity saveCustom(AlertEntity a) {
        entityManager.persist(a);
        entityManager.flush();
        return a;
    }


    public Optional<AlertEntity> findAllByIdCustom(Long id) {
        return Optional.of(entityManager.find(AlertEntity.class, id));
    }
}
