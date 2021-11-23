package it.polimi.db2.telecoApp.services.enums;

import io.swagger.models.auth.In;
import javassist.NotFoundException;

import java.util.Arrays;

public enum PackageType {
    FIXED_PHONE(0),
    FIXED_INTERNET(1),
    MOBILE_PHONE(2),
    MOBILE_INTERNET(3);

    PackageType(Integer id){
        this.id =id;
    }
    private final Integer id;

    public Integer getId() {
        return id;
    }

    public static PackageType getById(Integer id){
        return Arrays.stream(values())
                .filter(pt -> pt.id.equals(id))
                .findAny().orElseThrow();
    }


}
