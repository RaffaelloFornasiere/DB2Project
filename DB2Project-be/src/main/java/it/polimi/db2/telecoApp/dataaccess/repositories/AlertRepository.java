package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<AlertEntity, Long> {

}