package it.polimi.db2.telecoApp.services.models.packagedetails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@type")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public abstract class PackageDetails {
    protected Integer costMonth;
}
