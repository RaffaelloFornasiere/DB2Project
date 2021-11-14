package it.polimi.db2.telecoApp.services.enums;

public enum Role {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private Integer id;
    private Role(Integer id){
        this.id = id;
    }

    public Integer getId(){return id;}
}

