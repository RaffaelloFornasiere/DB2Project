package it.polimi.db2.telecoApp.dataaccess.repositories.custom.impl;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.custom.AlertRepositoryCustom;
import org.springframework.stereotype.Repository;
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

    public Optional<AlertEntity> findAllByIdCustom(Long id) {
        return Optional.of(entityManager.find(AlertEntity.class, id));
    }
}
