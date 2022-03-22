package it.polimi.db2.teleco_app.dataaccess.repositories.custom.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import it.polimi.db2.teleco_app.dataaccess.entities.OptionalPackageEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.OrderEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.OrderRepositoryCustom;
import org.apache.tomcat.jni.Time;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.TimeUnit;


public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PlatformTransactionManager platformTransactionManager;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderEntity saveCustom(OrderEntity orderEntity) {
        save(orderEntity);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            save(orderEntity);
        }).start();
        return orderEntity;
    }

    OrderEntity save(OrderEntity orderEntity) {
        if (orderEntity.getId() == null)
            entityManager.persist(orderEntity);
        else
            entityManager.merge(orderEntity);
        entityManager.flush();


        return orderEntity;
    }
}
