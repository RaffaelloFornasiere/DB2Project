package it.polimi.db2.teleco_app.dataaccess.repositories;

import it.polimi.db2.teleco_app.dataaccess.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    //create a method that returns a list of orders
    //you can use the name convention implemented by Spring JpaRepository
    //(https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
    //or you can write some JPQL with the @Query annotation above the method you want to call
    //or finally you can write @Query(nativeQuery = true, value """put here the mysql query""")
    //and put that above the method you want to call to retrieve the orders

    List<OrderEntity> findAllByUser_Username(String username);

    @Query(
            nativeQuery = true,
            value = """
                    select  * from orders where
                    order_date between ?1 and ?2
                    """
    )
    List<OrderEntity> findAllByOrderDateAfterAndOrderDateBeforeNative(LocalDateTime orderDate, LocalDateTime orderDate2);


    @Query(
            value = """
                    select o from OrderEntity o where o.orderDate between ?1 and ?2
                    """
    )
    List<OrderEntity> findAllByOrderDateAfterAndOrderDateBeforeJPQL(LocalDateTime orderDate, LocalDateTime orderDate2);


    @Query(
            nativeQuery = true,
            value = """
                   select * from orders  where package = ?1
                   """
    )
    List<OrderEntity> findAllByPackageIdNative(Long packageId);
}