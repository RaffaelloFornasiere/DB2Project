package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.ValidityPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidityPeriodRepository extends JpaRepository<ValidityPeriodEntity, Long> {
}
