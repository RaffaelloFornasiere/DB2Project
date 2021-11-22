package it.polimi.db2.telecoApp.services.impl;

import com.google.gson.Gson;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.packagedetails.MobileInternetDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PackageServiceImplTest {

    @Test
    void save() {
        Package test = Package.builder()
                .id(50L)
                .details(MobileInternetDetails.builder()
                        .GBs(100)
                        .extraGBsFee(3)
                        .costMonth(6)
                        .build())
                .name("test")
                .type("mobileInternet")
                .build();

        Gson gson = new Gson();
        System.out.println(gson.toJson(test));
    }
}