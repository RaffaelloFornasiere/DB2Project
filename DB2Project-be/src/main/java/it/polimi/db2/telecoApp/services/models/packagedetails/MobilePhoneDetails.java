package it.polimi.db2.telecoApp.services.models.packagedetails;

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
public class MobilePhoneDetails extends PackageDetails{
    private Integer sms;
    private Integer minutes;
    private Double extraSmsFee;
    private Double extraMinutesFee;
}
