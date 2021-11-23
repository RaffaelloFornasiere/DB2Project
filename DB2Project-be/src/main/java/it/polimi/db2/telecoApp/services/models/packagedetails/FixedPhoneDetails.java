package it.polimi.db2.telecoApp.services.models.packagedetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class FixedPhoneDetails implements PackageDetails{
//    private Integer costMonth;

}
