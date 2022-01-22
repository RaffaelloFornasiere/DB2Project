package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.BillingEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BillingRepository extends JpaRepository<BillingEntity, Integer> {

    @Query(
            nativeQuery = true,
            value = """
                    select * from billings
                    where order_id = ?1
                    order by billing_date_time desc
                    limit  1
                    """
    )
    BillingEntity findOneByOrderIdNative(Long orderId);

    @Query(
            nativeQuery = true,
            value = """
                    select * from billings
                    where order_id = ?1       
                    """
    )
    List<BillingEntity> findAllByOrderIdNative(Long orderId) ;
}
