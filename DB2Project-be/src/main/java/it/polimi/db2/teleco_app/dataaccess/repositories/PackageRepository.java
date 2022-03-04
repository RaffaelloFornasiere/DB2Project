package it.polimi.db2.teleco_app.dataaccess.repositories;

import it.polimi.db2.teleco_app.dataaccess.entities.ServicePackageEntity;
import it.polimi.db2.teleco_app.dataaccess.repositories.custom.PackageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<ServicePackageEntity, Long>, PackageRepositoryCustom {

}
