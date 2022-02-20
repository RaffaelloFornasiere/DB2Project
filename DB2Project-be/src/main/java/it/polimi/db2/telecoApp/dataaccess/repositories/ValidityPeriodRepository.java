package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.ValidityPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ValidityPeriodRepository extends JpaRepository<ValidityPeriodEntity, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select vp.* from validity_periods vp, ValidityPeriodPackage vpp, packages p
                    where vpp.packageId = p.package_id
                    and p.package_id = ?1
                    and vp.id = vpp.validityPeriodId
                                        """
    )
    List<ValidityPeriodEntity> findAllByPackageId(Long packageId);
}
