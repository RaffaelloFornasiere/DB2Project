package it.polimi.db2.teleco_app.dataaccess.repositories.custom;

import it.polimi.db2.teleco_app.dataaccess.entities.AlertEntity;

import java.util.List;

public interface AlertRepositoryCustom {
    List<AlertEntity> findAllCustom();

    AlertEntity saveCustom(AlertEntity a);
}
