package it.polimi.db2.telecoApp.services.models.packagedetails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@type") // Intended usage
@JsonSubTypes({@JsonSubTypes.Type(MobileInternetDetails.class),
        @JsonSubTypes.Type(MobilePhoneDetails.class),
        @JsonSubTypes.Type(FixedInternetDetails.class),
        @JsonSubTypes.Type(FixedPhoneDetails.class)
})
public interface PackageDetails {
}
