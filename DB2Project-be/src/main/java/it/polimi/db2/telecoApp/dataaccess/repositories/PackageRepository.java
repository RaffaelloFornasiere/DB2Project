package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.telecoApp.dataaccess.repositories.custom.PackageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<ServicePackageEntity, Long>, PackageRepositoryCustom {

}
