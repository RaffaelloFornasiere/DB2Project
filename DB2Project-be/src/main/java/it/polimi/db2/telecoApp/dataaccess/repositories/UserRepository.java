package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
