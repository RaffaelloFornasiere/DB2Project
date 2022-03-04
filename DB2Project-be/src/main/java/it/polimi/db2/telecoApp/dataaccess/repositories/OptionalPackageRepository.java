package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.OptionalPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public interface OptionalPackageRepository extends JpaRepository<OptionalPackageEntity, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select * from optional_packages o, packages_optional_packages pop where pop.id_service_package = ?1 and pop.id_optional_package = o.id
                    """
    )
    List<OptionalPackageEntity> findAllByPackageId(Long packageId);



}
