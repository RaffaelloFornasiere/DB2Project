package it.polimi.db2.telecoApp.dataaccess.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PurchasesRepositoryTest {

    @Autowired
    PurchasesRepository purchasesRepository;

    @BeforeEach
    public void setup(){
        assertThat(purchasesRepository).isNotNull();
    }

    @Test
    public void testDefaultMethods(){
        var res = purchasesRepository.findAll();
        assertThat(res).isNotNull();
        assertThat(res).isNotEmpty();

    }
}