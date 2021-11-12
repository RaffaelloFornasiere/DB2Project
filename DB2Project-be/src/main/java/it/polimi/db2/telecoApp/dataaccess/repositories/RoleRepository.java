package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
