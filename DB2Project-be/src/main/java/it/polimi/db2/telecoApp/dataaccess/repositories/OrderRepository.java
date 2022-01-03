package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    //create a method that returns a list of orders
    //you can use the name convention implemented by Spring JpaRepository
    //(https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
    //or you can write some JPQL with the @Query annotation above the method you want to call
    //or finally you can write @Query(nativeQuery = true, value """put here the mysql query""")
    //and put that above the method you want to call to retrieve the orders
}