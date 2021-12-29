package it.polimi.db2.telecoApp.dataaccess.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PackageRepositoryTest {
    @Autowired
    PackageRepository packageRepository;


    @Test
    void testFind(){
        var res = packageRepository.findById(14L);
        System.out.println(res);
    }
}