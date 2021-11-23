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
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PackageServiceImplTest {


    @Autowired
    PackageService packageService;

    @Test
    void testSave() {
        Package test = Package.builder()
                .id(50L)
                .details(MobilePhoneDetails.builder()
                                .sms(1000)
                                .minutes(1000)
                                .costMonth(7)
                                .extraMinutesFee(0.14)
                                .extraSmsFee(0.15)

//                        .gigabytes(4000)
//                        .extraGigaBytesFee(5)
//                        .costMonth(20)
                        .build())
                .name("test")
                .type(PackageType.MOBILE_INTERNET)
                .build();

        Package res = packageService.save(test);
        System.out.println(res);
    }
}