package it.polimi.db2.telecoApp.services.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.google.gson.Gson;
import io.swagger.models.auth.In;
import it.polimi.db2.telecoApp.dataaccess.entities.PackageEntity;
import it.polimi.db2.telecoApp.dataaccess.entities.PackageTypeEntity;
import it.polimi.db2.telecoApp.services.enums.PackageType;
import it.polimi.db2.telecoApp.services.models.Package;
import it.polimi.db2.telecoApp.services.models.packagedetails.PackageDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    PackageMapper MAPPER = Mappers.getMapper(PackageMapper.class);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "details", target = "details")
    Package toTarget(PackageEntity source);

    @Mapping(source = "id"  , target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "details", target = "details")
    PackageEntity toSource(Package source);


    default PackageType map(Integer value){
        return PackageType.getById(value);
    }

    default Integer map(PackageType value){
        return value.getId();
    }


    default PackageDetails map(String value){
        try {
            return new ObjectMapper().readValue(value, PackageDetails.class);
        } catch (JsonProcessingException e) {
            System.err.println("Impossibile deserializzare");
            System.err.println('\t' + value);
            return null;
        }
    }


    default String map(PackageDetails value){
        try {
            return new ObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            System.err.println("Impossibile serializzare:");
            System.err.println('\t' + value.toString());
            return "";
        }
    }
}
