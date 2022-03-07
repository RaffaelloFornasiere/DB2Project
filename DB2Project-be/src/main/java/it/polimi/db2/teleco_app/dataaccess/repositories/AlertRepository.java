package it.polimi.db2.teleco_app.dataaccess.repositories;

import it.polimi.db2.teleco_app.dataaccess.entities.AlertEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.AlertRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<AlertEntity, Long>,
        AlertRepositoryCustom {

}
