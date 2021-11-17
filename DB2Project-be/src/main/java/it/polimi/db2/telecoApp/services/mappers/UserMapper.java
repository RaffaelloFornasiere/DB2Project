package it.polimi.db2.telecoApp.services.mappers;

import it.polimi.db2.telecoApp.dataaccess.entities.RoleEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.UserEntity;
import it.polimi.db2.telecoApp.services.enums.Role;
import it.polimi.db2.telecoApp.services.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "birthdate", target = "birthdate")
    @Mapping(source = "billingAddr", target = "billingAddress")
    User toTarget(UserEntity source);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "birthdate", source = "birthdate")
    @Mapping(target = "billingAddr", source = "billingAddress")
    UserEntity toSource(User source);

    default Role map(RoleEntity entity) {
        return entity.getRole();
    }

    default RoleEntity map(Role role) {
        return new RoleEntity(role.getId().longValue(), role);
    }

}
