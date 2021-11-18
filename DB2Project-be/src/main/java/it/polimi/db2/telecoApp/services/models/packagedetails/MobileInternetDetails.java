package it.polimi.db2.telecoApp.services.models.packagedetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class MobileInternetDetails implements PackageDetails{
    private Integer GBs;
    private Integer extraGBsFee;
}
