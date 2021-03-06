package it.polimi.db2.teleco_app.services.enums;

public enum Role {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final Integer id;

    Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

