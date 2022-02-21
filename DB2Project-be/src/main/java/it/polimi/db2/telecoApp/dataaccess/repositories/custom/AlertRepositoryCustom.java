package it.polimi.db2.telecoApp.dataaccess.repositories.custom;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;

import java.util.List;

public interface AlertRepositoryCustom {
    List<AlertEntity> findAllCustom();
}
