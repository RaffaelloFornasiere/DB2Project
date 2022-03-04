package it.polimi.db2.teleco_app.dataaccess.repositories;

import it.polimi.db2.teleco_app.dataaccess.entities.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void testFindAll() {
        var list = orderRepository.findAll();
        assertThat(list).isNotNull().isNotEmpty();
    }


    @Test
    void testFindById() {
        Optional<OrderEntity> entity = orderRepository.findById(21L);
        assertThat(entity).isNotNull().isNotEmpty();
    }

}

