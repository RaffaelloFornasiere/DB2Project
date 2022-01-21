package it.polimi.db2.telecoApp.dataaccess.repositories;

import it.polimi.db2.telecoApp.services.mappers.ServiceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ServiceRepositoryTest {

    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServiceMapper serviceMapper;

    @Test
    void testFind(){
        var res = serviceRepository.findAll()
                .stream().map(serviceMapper::toTarget).toList();
        System.out.println(res);

    }
}