package it.polimi.db2.telecoApp.services.models.packagedetails;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class FixedInternetDetails implements PackageDetails{
    private Integer gigabytes;
    private Integer extraGigaBytesFee;
    private Integer initialCost;
    private Integer costMonth;

}
