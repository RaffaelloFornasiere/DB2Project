package it.polimi.db2.teleco_app.services.enums;

import java.util.Arrays;

public enum PackageType {
    FIXED_PHONE(0),
    FIXED_INTERNET(1),
    MOBILE_PHONE(2),
    MOBILE_INTERNET(3);

    private final Integer id;

    PackageType(Integer id) {
        this.id = id;
    }

    public static PackageType getById(Integer id) {
        return Arrays.stream(values())
                .filter(pt -> pt.id.equals(id))
                .findAny().orElseThrow();
    }

    public Integer getId() {
        return id;
    }


}
