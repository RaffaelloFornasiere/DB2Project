package it.polimi.db2.teleco_app.services.mappers;

import it.polimi.db2.teleco_app.dataaccess.entities.RoleEntity;
import it.polimi.db2.teleco_app.dataaccess.entities.UserEntity;
import it.polimi.db2.teleco_app.services.enums.Role;
import it.polimi.db2.teleco_app.services.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "email", target = "email")
    User toTarget(UserEntity source);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "email", source = "email")
    UserEntity toSource(User source);

    default Role map(RoleEntity entity) {
        return entity.getRole();
    }

    default RoleEntity map(Role role) {
        return new RoleEntity(role.getId().longValue(), role);
    }

}
