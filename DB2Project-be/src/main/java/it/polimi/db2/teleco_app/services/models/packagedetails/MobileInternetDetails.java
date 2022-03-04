package it.polimi.db2.teleco_app.services.models.packagedetails;

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
public class MobileInternetDetails extends ServiceDetails {
    private Integer gigabytes;
    private Integer extraGigaBytesFee;


}
