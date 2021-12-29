package it.polimi.db2.telecoApp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import it.polimi.db2.telecoApp.services.PackageService;
import it.polimi.db2.telecoApp.services.enums.PackageType;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.packagedetails.FixedInternetDetails;
import it.polimi.db2.telecoApp.services.models.packagedetails.FixedPhoneDetails;
import it.polimi.db2.telecoApp.services.models.packagedetails.MobileInternetDetails;
import it.polimi.db2.telecoApp.services.models.packagedetails.MobilePhoneDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PackageServiceImplTest {


    @Autowired
    PackageService packageService;

    @Test
    void testSave() {
        var res = packageService.findById(14L);
        System.out.println(res);
    }
}