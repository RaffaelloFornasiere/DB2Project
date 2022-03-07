package it.polimi.db2.teleco_app.dataaccess.repositories;

import it.polimi.db2.teleco_app.dataaccess.entities.AlertEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AlertRepositoryTest {

    @Autowired
    AlertRepository alertRepository;

    @Test
    void testCustomFind(){
        var res2 = alertRepository.findAll();
        assertThat(res2).isNotNull().isNotEmpty();
    }

    @Test
    void testSave(){
        var res2 = new AlertEntity(null, "admin");
        assertThatCode(() -> {
            alertRepository.saveCustom(res2);
            alertRepository.flush();
        }).doesNotThrowAnyException();
    }



}