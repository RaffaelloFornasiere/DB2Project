package it.polimi.db2.telecoApp.dataaccess.entities;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class OrderEntity {

    @Id
    private long id;

    @Column( name = "order_date")
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn( name = "user")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn( name = "package")
    private ServicePackageEntity servicePackageEntity;

    @ManyToOne
    @JoinColumn( name= "validityPeriod")
    private ValidityPeriodEntity validityPeriod;

    @Column( name = "startDate")
    private LocalDate startDate;

}
