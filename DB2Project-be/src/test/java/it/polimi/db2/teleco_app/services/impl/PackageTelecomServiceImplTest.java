package it.polimi.db2.teleco_app.services.impl;

import it.polimi.db2.teleco_app.services.PackageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PackageTelecomServiceImplTest {


    @Autowired
    PackageService packageService;

    @Test
    void testSave() {
        var res = packageService.findById(14L);
        System.out.println(res);
    }
}