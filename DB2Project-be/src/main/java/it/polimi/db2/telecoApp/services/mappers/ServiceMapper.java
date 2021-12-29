package it.polimi.db2.telecoApp.services.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.db2.telecoApp.dataaccess.entities.ServiceEntity;
import it.polimi.db2.telecoApp.services.models.packagedetails.ServiceDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import it.polimi.db2.telecoApp.services.models.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceMapper MAPPER = Mappers.getMapper(ServiceMapper.class);

    @Mapping(target = "serviceId", source = "serviceId")
    @Mapping(target = "serviceName", source = "serviceName")
    @Mapping(target = "serviceDetails", source = "serviceDetails")
    Service toTarget(ServiceEntity source);


    @Mapping(source = "serviceId", target = "serviceId")
    @Mapping(source = "serviceName", target = "serviceName")
    @Mapping(source = "serviceDetails", target = "serviceDetails")
    ServiceEntity toSource(Service source);


    default ServiceDetails map(String value){
        try {
            return new ObjectMapper().readValue(value, ServiceDetails.class);
        } catch (JsonProcessingException e) {
            System.err.println("Impossibile deserializzare");
            System.err.println('\t' + value);
            return null;
        }
    }

    default String map(ServiceDetails value){
        try {
            return new ObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            System.err.println("Impossibile serializzare:");
            System.err.println('\t' + value.toString());
            return "";
        }
    }
}
