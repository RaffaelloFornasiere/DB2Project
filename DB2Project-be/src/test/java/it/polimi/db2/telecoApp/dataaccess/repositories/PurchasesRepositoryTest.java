package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PurchasesRepositoryTest {

    @Autowired
    PurchasesRepository purchasesRepository;

    @Test
    void testFindAll() {
        var list = purchasesRepository.findAll();
        assertThat(list).isNotNull();
    }


    @Test
    void testFindById() {
        Optional<OrderEntity> entity = purchasesRepository.findById(21L);
        assertThat(entity).isNotNull();
        assertThat(entity).isNotEmpty();
    }

    @Test
    void testFindByIdUser() {
        //retrieve data using spring auto-built queries
        List<OrderEntity> entities = purchasesRepository.findAllByUserEntity_NameAndUserEntity_Surname("Salvi", "Petronilla");
        assertThat(entities).isNotNull();
        assertThat(entities).isNotEmpty();

        //retrieve data using jpql query
        List<OrderEntity> entities2 = purchasesRepository.findAllIdByUserNameAndSurname("Salvi", "Petronilla");
        assertThat(entities2).isNotNull();
        assertThat(entities2).isNotEmpty();

        //retrieve data using native query
        List<OrderEntity> entities3 = purchasesRepository.findAllIdByUserNameAndSurnameNATIVE("Salvi", "Petronilla");
        assertThat(entities3).isNotNull();
        assertThat(entities3).isNotEmpty();

        assertThat(entities).usingRecursiveComparison().isEqualTo(entities2);
        assertThat(entities2).usingRecursiveComparison().isEqualTo(entities3);


    }


}

