package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.dataaccess.entities.AlertEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AlertRepositoryTest {

    @Autowired
    AlertRepository alertRepository;

    @Test
    public void testCustomFind(){
//        var res = alertRepository.findAllCustom();
//        assertThat(res).isNotEmpty();
//        System.out.println(res);


        var res2 = alertRepository.findAll();
        System.out.println(res2);
    }

    @Test
    public void testSave(){
//        var res = alertRepository.findAllCustom();
//        assertThat(res).isNotEmpty();
//        System.out.println(res);


        var list = alertRepository.findAll();
        var res2 = new AlertEntity(null, "admin");
        alertRepository.saveCustom(res2);

        alertRepository.flush();
        System.out.println(res2);
    }



}