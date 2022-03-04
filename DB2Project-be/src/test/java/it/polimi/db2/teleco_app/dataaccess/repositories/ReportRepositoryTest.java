package it.polimi.db2.teleco_app.dataaccess.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportRepositoryTest {

    @Autowired
    ReportRepository repository;

    @Test
    void testRepo(){
        var aux = repository.findAllCustom();
        System.out.println(aux);
    }
}