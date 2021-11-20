package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.PurchaseEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface PurchasesRepository extends JpaRepository<PurchaseEntity, Long> {

    // the method becomes wayyyyy to long then usual, but this way you spring automatically
    // converts the query in jpql. Keep in mind that this is convenient thanks to
    // IntelliJ that suggest what to write, otherwise it would be useless.
    // anyways here you have the docs https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    Optional<PurchaseEntity> findPurchaseEntityByPurchaseDateAndUserEntity(LocalDateTime purchase_date, UserEntity user);

    //sometimes tho, the methods are so long that you feel bad just watching them. Let's give an example:
    Optional<PurchaseEntity> findDistinctFirstByPurchaseDateAfterAndUserEntityBirthdateAndPackageEntityNameOrPurchaseDateLessThanOrderById(LocalDateTime purchaseDate, LocalDate userEntity_birthdate, String packageEntity_name, LocalDateTime purchaseDate2);
    //I guess that you understand by yourself that even if this will compile and work, using (but also just declaring)
    //such a method will define your failure as a programmer
    //So what can we do? Writing the queries all the time is a pain in the ass, but it is the only way, I know, to avoid such long names
    //If you want to read all the reference (https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)
    //you'll probably come up with some more valid options, but I don't think we'll need to do such complex queries.
    //If we'll need, we'll try to use some other techniques (like this https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example)




    List<PurchaseEntity> findAllByUserEntity_NameAndUserEntity_Surname(String userEntity_name, String userEntity_surname);


    //here is the same method but with a jpql query
    @Query(
            "select p from PurchaseEntity p join p.userEntity u where u.name = :name and u.surname = :surname"
    )
    List<PurchaseEntity> findAllIdByUserNameAndSurname(String name, String surname);

    //here is the same query in native sql (so mysql)
    @Query(
            nativeQuery = true,
            value = """
                    select p.* from purchases p, users u\s
                    where p.user = u.username\s
                    and u.name = :name
                    and u.surname = :surname
                    """
    )
    List<PurchaseEntity> findAllIdByUserNameAndSurnameNATIVE(String name, String surname);


}