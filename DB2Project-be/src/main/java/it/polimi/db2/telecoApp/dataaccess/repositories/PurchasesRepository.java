package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchasesRepository extends JpaRepository<PurchaseEntity, Long> {
  }