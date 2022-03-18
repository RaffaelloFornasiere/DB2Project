package it.polimi.db2.teleco_app.services.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.db2.teleco_app.dataaccess.entities.ServiceEntity;
import it.polimi.db2.teleco_app.services.models.TelecomService;
import it.polimi.db2.teleco_app.services.models.packagedetails.ServiceDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ServiceMapper {

    @Mapping(target = "id", source = "serviceId")
    @Mapping(target = "name", source = "serviceName")
    @Mapping(target = "details", source = "serviceDetails")
    TelecomService toTarget(ServiceEntity source);


    @Mapping(source = "id", target = "serviceId")
    @Mapping(source = "name", target = "serviceName")
    @Mapping(source = "details", target = "serviceDetails")
    ServiceEntity toSource(TelecomService source);


    default ServiceDetails map(String value) {
        try {
            return new ObjectMapper().readValue(value, ServiceDetails.class);
        } catch (JsonProcessingException e) {
            System.err.println("Impossibile deserializzare");
            System.err.println('\t' + value);
            return null;
        }
    }

    default String map(ServiceDetails value) {
        try {
            return new ObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            System.err.println("Impossibile serializzare:");
            System.err.println('\t' + value.toString());
            return "";
        }
    }
}
