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

    List<OrderEntity> findAllByUser_UsernameOrderByOrderDate(String username);


    @Query(
            nativeQuery = true,
            value = """
                                select o.* from orders o join activation_schedule acts on o.order_id = acts.order_id
                                where o.suspended =0 and CURDATE() > acts.activation_date and CURDATE() < acts.deactivation_date and user = ?1
                    """
    )
    List<OrderEntity> findAllActiveByUser(String username);

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
                    select * from orders  where package_id = ?1
                    """
    )
    List<OrderEntity> findAllByPackageIdNative(Long packageId);

    @Query(
            value = """
                         select o from OrderEntity o order by o.orderDate
                    """
    )
    List<OrderEntity> findAllByOrderByOrderDate();
//
//    @Query(
//
////            value = """
////                    SELECT  DATE(o.orderDate) Date, COUNT(DISTINCT order_id) totalCOunt
////                    FROM    OrderEntity o
////                    GROUP   BY  DATE(o.order_date)
////                    """
//    )
//    List<Object[2]> getOrdersPerDay();

    @Query(
            nativeQuery = true,
//            value = "select  from orders o "
            value = "select DATE(o.order_date), count(distinct o.order_id) from orders o group by DATE(o.order_date)"
    )
    List<Object[]> getOrdersPerDay();
}